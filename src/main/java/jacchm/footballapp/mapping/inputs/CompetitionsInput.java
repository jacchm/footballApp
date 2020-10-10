package jacchm.footballapp.mapping.inputs;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionsInput {

    private int id;
    private Integer count;
//    this is omitted because all competitions are always necessary to get
//    private List<String> filters;
    private List<CompetitionDTO> competitions;

}