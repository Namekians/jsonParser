import dataModel.jsonObject;
import exceptions.SyntaxErrorException;

/**
 * Created by yinch_000 on 7/2/2016.
 */
class test {
    public static void main(String[] args) throws SyntaxErrorException {

        String json = "{\n" +
                "    \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "    \"title\": \"Product\",\n" +
                "    \"description\": \"A product from Acme's catalog\",\n" +
                "    \"type\": false,\n" +
                "    \"properties\": {\n" +
                "        \"id\": {\n" +
                "            \"description\": \"The unique identifier for a product\",\n" +
                "            \"type\": 3.2\n" +
                "        },\n" +
                "        \"name\": {\n" +
                "            \"description\": \"Name of the product\",\n" +
                "            \"type\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"required\": [\"id\", \"name\"]\n" +
                "}";

        System.out.println("char at: "+json.charAt(13));
        jsonParser parser = new jsonParser();
        jsonObject obj = parser.parseObject(json);


        System.out.println("type of the result is: " + obj.typeOf());


    }


}
