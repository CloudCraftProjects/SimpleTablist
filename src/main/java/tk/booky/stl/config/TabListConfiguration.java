package tk.booky.stl.config;
// Created by booky10 in SimpleTablist (15:22 30.06.21)

public class TabListConfiguration implements ConfigurationSavable {

    public String header = "";
    public String footer = "";

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}