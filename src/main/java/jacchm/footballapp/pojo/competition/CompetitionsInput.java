package jacchm.footballapp.pojo.competition;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CompetitionsInput {

    private int id;
    private Integer count;
//    this is omitted because all competitions are always necessary to get
//    private List<String> filters;
    private List<Competition> competitions;


}
