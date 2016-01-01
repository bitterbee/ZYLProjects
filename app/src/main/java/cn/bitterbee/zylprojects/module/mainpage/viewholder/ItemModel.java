package cn.bitterbee.zylprojects.module.mainpage.viewholder;

/**
 * Created by zyl06 on 1/1/16.
 */
public class ItemModel {
    private String mContent;
    private Class mActivityClass;

    public ItemModel(String content, Class activityClass) {
        this.mContent = content;
        this.mActivityClass = activityClass;
    }

    public String getContent() {
        return mContent;
    }

    public Class getActivityClass() {
        return mActivityClass;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public void setActivityClass(Class acitivityClass) {
        this.mActivityClass = acitivityClass;
    }
}
