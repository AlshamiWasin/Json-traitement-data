package BO;

import javax.persistence.Embeddable;

@Embeddable
public class InfoPersonnel {
    private String identite;
    private String url;

    public InfoPersonnel() {
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InfoPersonnel{");
        sb.append("identite='").append(identite).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
