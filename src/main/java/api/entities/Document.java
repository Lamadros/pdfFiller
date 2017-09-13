package api.entities;

import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

public class Document extends ApiObject {
    private String getDocumentsSchema = "api/schema/GetDocuments.json";
    private String createDocumentUrl = "api/schema/CreateDocumentFromUrl.json";
    private String documentPath = "/document";
    //private String documentsCountFilter = "items.find { it.name == \"%s\" }.documents_count";
    private String documentsName = "items.name";
    private String createFileBody = "src/main/resources/api/requestBody/createFile.json";


    @Step("Creating document from URL")
    public void createDocumentFromUrl(String docUrl) {
        String bodyData = String.format(generateStringFromResource(createFileBody), docUrl);
        post(documentPath, bodyData);
        assertResponseStructure(createDocumentUrl);
    }

    private void getDocuments() {
        get(documentPath);
        assertResponseStructure(getDocumentsSchema);
    }

    @Step("Get documents name via API")
    public List<String> getDocumentsName() {
        getDocuments();
        return getListsOf(documentsName);
    }






}
