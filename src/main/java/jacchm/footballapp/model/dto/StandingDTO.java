package jacchm.footballapp.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class StandingDTO {

    private Integer id;
    private String stage;
    private String type;
    private String group;
    private List<TableInputDTO> table;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<TableInputDTO> getTable() {
        return table;
    }

    public void setTable(List<TableInputDTO> table) {
        this.table = table;
    }
}

