package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ArticleDao { // dao.Article data access object

    private PostgreSQLConnection postgreSQLConnection;

    public ArticleDao(PostgreSQLConnection postgreSQLConnection) {
        this.postgreSQLConnection = postgreSQLConnection;
    }

    public List<Article> allArticles() throws SQLException {
        List<Article> articles = new LinkedList<>();
        ResultSet articlesResults = this.postgreSQLConnection.readOperation("select * from articles;");

        while (articlesResults.next()) {
            Article a = new Article(
                    articlesResults.getLong("articleid"),
                    articlesResults.getLong("userid"),
                    articlesResults.getString("title"),
                    articlesResults.getInt("likes")
            );
            articles.add(a);
        }
        return articles;
    }

    public List<Article> getArticlesByUserid (long userId) throws SQLException {
        List<Article> articles = new LinkedList<>();
        ResultSet articlesResult = this.postgreSQLConnection.readOperation("SELECT * FROM ARTICLES WHERE userid=" + userId +";");

        while (articlesResult.next()) {
            Article a = new Article(articlesResult.getLong("articleid"), articlesResult.getLong("userId"), articlesResult.getString("title"), articlesResult.getInt("likes"));
            articles.add(a);
        }

        return articles;
    }

    public boolean addArticle (long userid, String title) throws SQLException {
        int insertArticles = this.postgreSQLConnection.updateOperation("insert into articles (userid, title, likes) values ('" + userid + "', '" + title + "', '0');");

        return insertArticles > 0;
    }

    public boolean likeArticle (String title) throws SQLException {
        int likes = 0;
        ResultSet likesResult = this.postgreSQLConnection.readOperation("select likes from articles where title='"+title+"';");

        while (likesResult.next()) {
            likes = likesResult.getInt("likes") + 1;
        }

        int updateLikes = this.postgreSQLConnection.updateOperation("update articles set likes="+likes+" where title='"+title+"';");
        return updateLikes > 0;
    }
}


