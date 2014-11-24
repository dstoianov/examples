package convertObjectToMap;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;

/**
 * Created by dstoianov on 2014-11-24, 3:10 PM
 */
public class ConvertToMap {


    @Test(description = "Straight up Java")
    public void convert_object_to_map_java() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        NoteBook actionMethodNoteBook = new NoteBook(100, "Action Method Notebook");

        Map<String, Object> objectAsMap = new HashMap<String, Object>();
        BeanInfo info = Introspector.getBeanInfo(actionMethodNoteBook.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null)
                objectAsMap.put(pd.getName(), reader.invoke(actionMethodNoteBook));
        }

        assertThat(objectAsMap, hasEntry("numberOfSheets", (Object) new Double(100.0)));
        assertThat(objectAsMap, hasEntry("description", (Object) "Action Method Notebook"));
    }

    @Test(description = "Apache Commons")
    public void convert_object_to_map_apache_commons() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        NoteBook fieldNoteBook = new NoteBook(878, "Field Notebook");

        @SuppressWarnings("unchecked")
        Map<String, Object> objectAsMap = BeanUtils.describe(fieldNoteBook);

        assertThat(objectAsMap, hasEntry("numberOfSheets", (Object) "878.0"));
        assertThat(objectAsMap, hasEntry("description", (Object) "Field Notebook"));
    }

    @Test(description = "Jackson")
    public void convert_object_to_map_jackson() {

        NoteBook moleskineNoteBook = new NoteBook(200, "Moleskine Notebooks");

        ObjectMapper objectMapper = new ObjectMapper();

        @SuppressWarnings("unchecked")
        Map<String, Object> objectAsMap = objectMapper.convertValue(moleskineNoteBook, Map.class);

        assertThat(objectAsMap, hasEntry("numberOfSheets", (Object) new Double(200.0)));
        assertThat(objectAsMap, hasEntry("description", (Object) "Moleskine Notebooks"));
    }


}
