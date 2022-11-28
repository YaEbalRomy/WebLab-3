import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@SessionScoped
@ManagedBean(name = "pointsBean")
public class PointsBean implements Serializable {
    private List<Point> pointsCollection = new ArrayList<>();
    private Point point = new Point();
    private String graphR = "2";

    public void setGraphR() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        graphR = params.get("r");
    }

    /*
    public void setGraphR() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String r = params.get("r");
        for (Point point : pointsCollection) {
            point.setR(r);
        }
    }
    */
    public void uploadPoints() {
        pointsCollection = DatabaseManager.getInstance().getCollectionFromDataBase();
    }

    public void clear() {
        DatabaseManager.getInstance().removeAllPoints();
        pointsCollection.clear();
    }

    public void submitPoints() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        point.setX(params.get("x"));
        point.setY(params.get("y"));
        point.setR(params.get("r"));
        addPointAndCalculatedResultToDatabase(point);
    }

    @SneakyThrows
    private void addPointAndCalculatedResultToDatabase(Point point) {
        point.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
        point.setResult(String.valueOf(AreaResultChecker.isPointInArea(point)));
        String transaction = DatabaseManager.getInstance().addPoint((Point) point.clone());
        if (transaction.equals("commit")) {
            pointsCollection.add((Point) point.clone());
            //uploadPoints();
        }
    }
}