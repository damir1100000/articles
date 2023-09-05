package dao;

public class Article {
    private long articleId;
    private long useriId;
    private String title;
    private int likes;

    public Article(long articleId, long useriId, String title, int likes) {
        this.articleId = articleId;
        this.useriId = useriId;
        this.title = title;
        this.likes = likes;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getUseriId() {
        return useriId;
    }

    public void setUseriId(long useriId) {
        this.useriId = useriId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
