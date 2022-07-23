package sec03.brd08;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");		// Oracle 연동
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List selectAllArticles(Map pagingMap){
		List articlesList = new ArrayList();
		int section = (Integer)pagingMap.get("section");
		int pageNum=(Integer)pagingMap.get("pageNum");
		try{
		   conn = dataFactory.getConnection();
		   String query ="SELECT * FROM ( "
						+ "select ROWNUM  as recNum,"+"LVL,"
							+"articleNO,"
							+"parentNO,"
							+"title,"
							+"id,"
							+"writeDate"
				                  +" from (select LEVEL as LVL, "
								+"articleNO,"
								+"parentNO,"
								+"title,"
								+"id,"
								 +"writeDate"
							   +" from t_board" 
							   +" START WITH  parentNO=0"
							   +" CONNECT BY PRIOR articleNO = parentNO"
							  +"  ORDER SIBLINGS BY articleNO DESC)"
					+") "                        
					+" where recNum between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10";         // 계층형으로 조회     
		   System.out.println(query);
		   pstmt= conn.prepareStatement(query);
		   pstmt.setInt(1, section);
		   pstmt.setInt(2, pageNum);
		   pstmt.setInt(3, section);
		   pstmt.setInt(4, pageNum);
		   ResultSet rs =pstmt.executeQuery();
		   while(rs.next()){
		      int level = rs.getInt("level");  // 각 글의 깊이(계층)를 level속성에 저장	
		      int articleNO = rs.getInt("articleNO");	// 글 번호
		      int parentNO = rs.getInt("parentNO");
		      String title = rs.getString("title");
		      String id = rs.getString("id");
		      Date writeDate= rs.getDate("writeDate");
		      ArticleVO article = new ArticleVO();
		      article.setLevel(level);
		      article.setArticleNO(articleNO);
		      article.setParentNO(parentNO);
		      article.setTitle(title);
		      article.setId(id);
		      article.setWriteDate(writeDate);
		      articlesList.add(article);	
		   } //end while
		   rs.close();
		   pstmt.close();
		   conn.close();
	  }catch(Exception e){
	     e.printStackTrace();	
	  }
	  return articlesList;
    } 
	
	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate" + " from t_board"
					+ " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
					+ " ORDER SIBLINGS BY articleNO DESC";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}


	private int getNewArticleNO() {
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT  max(articleNO) from t_board ";	// 기본 글 번호중 가장 큰 번호를 조회
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if (rs.next())	
				return (rs.getInt(1) + 1);  // 가장 큰 번호에 1을 더한 번호를 반환
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int insertNewArticle(ArticleVO article) {
		int articleNO = getNewArticleNO();	// 새 글을 추가하기 전에 새 글에 대한 글번호를 가져옴
		try {
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
					+ " VALUES (?, ? ,?, ?, ?, ?)";	// insert문을 이용해 글정보 추가
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return articleNO;
	}

	public ArticleVO selectArticle(int articleNO) {
		ArticleVO article = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select articleNO,parentNO,title,content, imageFileName,id,writeDate" + " from t_board"
					+ " where articleNO=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _articleNO = rs.getInt("articleNO");
			int parentNO = rs.getInt("parentNO");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName = rs.getString("imageFileName");
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");

			article.setArticleNO(_articleNO);
			article.setParentNO(parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	public void updateArticle(ArticleVO article) {
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		String imageFileName = article.getImageFileName();
		try {
			conn = dataFactory.getConnection();
			String query = "update t_board  set title=?,content=?";
			if (imageFileName != null && imageFileName.length() != 0) {
				query += ",imageFileName=?";
			}
			query += " where articleNO=?";

			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			if (imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteArticle(int articleNO) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM t_board ";
			query += " WHERE articleNO in (";
			query += "  SELECT articleNO FROM  t_board ";
			query += " START WITH articleNO = ?";
			query += " CONNECT BY PRIOR  articleNO = parentNO )";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Integer> selectRemovedArticles(int articleNO) {
		List<Integer> articleNOList = new ArrayList<Integer>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT articleNO FROM  t_board  ";
			query += " START WITH articleNO = ?";
			query += " CONNECT BY PRIOR  articleNO = parentNO";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				articleNO = rs.getInt("articleNO");
				articleNOList.add(articleNO);
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
	}

	public int selectTotArticles() {
		try {
			conn = dataFactory.getConnection();
			String query = "select count(articleNO) from t_board ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				return (rs.getInt(1));
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}