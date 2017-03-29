package deothan.dk.jsonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ObjectMapper objectMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objectMapper = new ObjectMapper();

        String json = writeDataContainerAsJson(new DataContainer(1, "test"));
        readDataContainerFromJson(json);
    }

    public String writeDataContainerAsJson(DataContainer dataContainer) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(dataContainer);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public DataContainer readDataContainerFromJson(String json) {
        DataContainer dataContainer = null;

        try {
            dataContainer = objectMapper.readValue(json, DataContainer.class);
            System.out.println(dataContainer.getId());
            System.out.println(dataContainer.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataContainer;
    }
}
