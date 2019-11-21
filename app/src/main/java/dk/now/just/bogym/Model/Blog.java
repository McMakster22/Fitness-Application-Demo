package dk.now.just.bogym.Model;

public class Blog {
    private int blogImage_id;
    private String blogName;
    private String blogBody;

    public Blog(int blogImage_id, String blogName, String blogBody) {
        this.blogImage_id = blogImage_id;
        this.blogName = blogName;
        this.blogBody = blogBody;
    }

    public int getBlogImage_id() {
        return blogImage_id;
    }

    public void setBlogImage_id(int blogImage_id) {
        this.blogImage_id = blogImage_id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public void setBlogBody(String blogBody) {
        this.blogBody = blogBody;
    }
}
