import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonParser {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File("SampleTestCase.json"));

        // Parse the "keys" object
        JsonNode keysNode = rootNode.get("keys");
        int n = keysNode.get("n").asInt();
        int k = keysNode.get("k").asInt();
        System.out.println("n: " + n);
        System.out.println("k: " + k);

        // Parse the roots
        for (JsonNode root : rootNode) {
            if (!root.get("keys").equals(rootNode.get("keys"))) {
                int x = Integer.parseInt(root.asText());
                JsonNode rootObject = rootNode.get(root.asText());
                String base = rootObject.get("base").asText();
                String value = rootObject.get("value").asText();
                int y = decodeValue(base, value);
                System.out.println("Root: x = " + x + ", y = " + y);
            }
        }
    }

    private static int decodeValue(String base, String value) {
        int y = 0;
        for (char c : value.toCharArray()) {
            y = y * Integer.parseInt(base) + Integer.parseInt(String.valueOf(c), Integer.parseInt(base));
        }
        return y;
    }
}