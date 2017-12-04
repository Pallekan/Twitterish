import java.util.List;
import java.util.LinkedList;

public class Feed {
    private List<Post> posts = new LinkedList<Post>();

    public void addPost(Post post) {
        posts.add(0, post);
    }

    public String renderAll() {
        return this.render(posts.size());
    }

    public String renderLatest(int n) {
        return this.render(n);
    }

    private String render(int n) {
        String result = "";

        for (Post p : this.posts) {
            result = result + p.render();
            if (--n < 0) break;
        }

        return result;
    }

    //renders the posts of all friends of the account a and ignores all accounts on the ignored list.
    public String filterRender(Account a)
    {
        String answer = "";
        for (Post p : this.posts)
            {
                if (!(a.isCurrentlyIgnoring(p.getPoster())))
                    {
                    answer = answer+"{"+a.getFriendsName(p.getPoster())+"} says:\n"+p.renderOnlyContent()+ "\n";
                    }
            }
        return answer;
    }
}
