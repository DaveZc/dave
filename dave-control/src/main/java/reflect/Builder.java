package reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chen.z
 * @date 2018/7/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Builder extends CommonFather implements Serializable, BuildingI {

    private String buildingName;

    private Long high;

    private Integer floor;

    private Double area;

    private Long cost;

    private Date startWorkTime;

    private Date startBuildTime;

    private Date endBuildTime;

    public Builder(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public void building() {

    }
}
