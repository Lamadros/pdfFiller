package api;

import api.entities.Document;
import api.entities.Folder;

public class API {
    Document document;
    Folder folder;

    public Document document() {
        if (this.document == null) {
            this.document = new Document();
        }
        return document;
    }

    public Folder folder() {
        if (this.folder == null) {
            this.folder = new Folder();
        }
        return folder;
    }
}
