package api.entities;

import ru.yandex.qatools.allure.annotations.Step;

import static com.jayway.restassured.path.json.JsonPath.from;

public class Folder extends ApiObject {
    private String createFolderSchema = "api/schema/CreateFolder.json";
    private String createFolderBody = "src/main/resources/api/requestBody/CreateFolder.json";
    private String folderPath = "/folder";
    private String documentsCountFilter = "items.find { it.name == \"%s\" }.documents_count";


    @Step("Crete folder \"{0}\" via API ")
    public void createFolder(String folderName) {
        String bodyData = String.format(generateStringFromResource(createFolderBody), folderName);
        post(folderPath, bodyData);
        assertResponseStructure(createFolderSchema);
    }

    @Step("Get count of elements in {0} folder")
    public int getDocumentCount(String folderName) {
        get(folderPath);
        int count = from(getResponseAsString()).get(String.format(documentsCountFilter, folderName));
        return count;
    }
}
