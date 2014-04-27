package xmltoobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * Created by Funker on 27.04.14.
 */

@XmlRootElement(name = "ROWDATA")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rowdata implements RandomObject {

    @XmlElement(name = "ROW")
    private List<Row> rows = null;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public Row getRandom() {
        Collections.shuffle(rows);
        return rows.get(0);
        //return this.employees.get(new Random().nextInt(this.employees.size()-1));
    }

}
