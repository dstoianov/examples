package elementalselenium;

/**
 * Created by dstoianov on 2015-06-30.
 */
public class ESBean implements Comparable<ESBean> {
    private int number;
    private String title;
    private String href;
    private String tags;
    private String meta;
    private String body;

    public ESBean(int number, String title, String href, String tags, String meta) {
        this.number = number;
        this.title = title;
        this.href = href;
        this.tags = tags;
        this.meta = meta;
    }

    public int getNumber() {
        return number;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    public String getTags() {
        return tags;
    }

    public String getMeta() {
        return meta;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int compareTo(ESBean o) {
        int compareDate = ((ESBean) o).getNumber();
        //ascending order
        return this.number - (compareDate);
        //descending order
//        return compareDate.compareTo(this.date);
    }
}

