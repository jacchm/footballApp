package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.*;
import jacchm.footballapp.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/* TODO: Simplify this interface. Mapstruct was not working correctly - some of the fields in the mapped objects were
*        missing. Default methods have been added to map classes correctly. */

@Mapper(componentModel = "spring")
public interface StandingsInputMapper {

    StandingsInputMapper INSTANCE = Mappers.getMapper(StandingsInputMapper.class);

    StandingsInput standingsInputDtoToStandingsInput(StandingsInputDTO standingsInputDTO);
    StandingsInputDTO standingsInputToStandingsInputDto(StandingsInput standingsInput);

    default StandingDTO standingToStandingDto(Standing standing) {
        if ( standing == null ) {
            return null;
        }

        StandingDTO standingDTO = new StandingDTO();

        standingDTO.setType( standing.getStandingType() );
        standingDTO.setGroup( standing.getStandingGroup() );
        standingDTO.setTable( tableInputListToTableInputDTOList( standing.getLeagueTable() ) );
        standingDTO.setId( standing.getId() );
        standingDTO.setStage( standing.getStage() );

        return standingDTO;
    }

    default List<TableInputDTO> tableInputListToTableInputDTOList(List<TableInput> list) {
        if ( list == null ) {
            return null;
        }

        List<TableInputDTO> list1 = new ArrayList<TableInputDTO>( list.size() );
        for ( TableInput tableInput : list ) {
            list1.add( tableInputToTableInputDTO( tableInput ) );
        }

        return list1;
    }

    default TableInputDTO tableInputToTableInputDTO(TableInput tableInput) {
        if ( tableInput == null ) {
            return null;
        }

        TableInputDTO tableInputDTO = new TableInputDTO();

        tableInputDTO.setId( tableInput.getId() );
        if ( tableInput.getPosition() != null ) {
            tableInputDTO.setPosition( tableInput.getPosition() );
        }
        tableInputDTO.setTeam( teamToTeamDTO( tableInput.getTeam() ) );
        if ( tableInput.getPlayedGames() != null ) {
            tableInputDTO.setPlayedGames( tableInput.getPlayedGames() );
        }
        tableInputDTO.setForm( tableInput.getForm() );
        if ( tableInput.getWon() != null ) {
            tableInputDTO.setWon( tableInput.getWon() );
        }
        if ( tableInput.getDraw() != null ) {
            tableInputDTO.setDraw( tableInput.getDraw() );
        }
        if ( tableInput.getLost() != null ) {
            tableInputDTO.setLost( tableInput.getLost() );
        }
        if ( tableInput.getPoints() != null ) {
            tableInputDTO.setPoints( tableInput.getPoints() );
        }
        if ( tableInput.getGoalsFor() != null ) {
            tableInputDTO.setGoalsFor( tableInput.getGoalsFor() );
        }
        if ( tableInput.getGoalsAgainst() != null ) {
            tableInputDTO.setGoalsAgainst( tableInput.getGoalsAgainst() );
        }
        if ( tableInput.getGoalDifference() != null ) {
            tableInputDTO.setGoalDifference( tableInput.getGoalDifference() );
        }

        return tableInputDTO;
    }

    default TeamDTO teamToTeamDTO(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamDTO teamDTO = new TeamDTO();

        if ( team.getId() != null ) {
            teamDTO.setId( team.getId() );
        }
        teamDTO.setArea( areaToAreaDTO( team.getArea() ) );
        teamDTO.setName( team.getName() );
        teamDTO.setShortName( team.getShortName() );
        teamDTO.setTla( team.getTla() );
        teamDTO.setCrestUrl( team.getCrestUrl() );
        teamDTO.setAddress( team.getAddress() );
        teamDTO.setPhone( team.getPhone() );
        teamDTO.setWebsite( team.getWebsite() );
        teamDTO.setEmail( team.getEmail() );
        teamDTO.setFounded( team.getFounded() );
        teamDTO.setClubColors( team.getClubColors() );
        teamDTO.setVenue( team.getVenue() );
        teamDTO.setLastUpdated( team.getLastUpdated() );

        return teamDTO;
    }

    default AreaDTO areaToAreaDTO(Area area) {
        if ( area == null ) {
            return null;
        }

        AreaDTO areaDTO = new AreaDTO();

        if ( area.getId() != null ) {
            areaDTO.setId( area.getId() );
        }
        areaDTO.setName( area.getName() );
        areaDTO.setCountryCode( area.getCountryCode() );
        areaDTO.setEnsignUrl( area.getEnsignUrl() );

        return areaDTO;
    }

    default Standing standingDtoToStanding(StandingDTO standingDTO) {
        if ( standingDTO == null ) {
            return null;
        }

        Standing standing = new Standing();

        standing.setStandingType( standingDTO.getType() );
        standing.setStandingGroup( standingDTO.getGroup() );
        standing.setLeagueTable( tableInputDTOListToTableInputList( standingDTO.getTable() ) );
        standing.setId( standingDTO.getId() );
        standing.setStage( standingDTO.getStage() );

        return standing;
    }

    default List<TableInput> tableInputDTOListToTableInputList(List<TableInputDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<TableInput> list1 = new ArrayList<TableInput>( list.size() );
        for ( TableInputDTO tableInputDTO : list ) {
            list1.add( tableInputDTOToTableInput( tableInputDTO ) );
        }

        return list1;
    }

    default TableInput tableInputDTOToTableInput(TableInputDTO tableInputDTO) {
        if ( tableInputDTO == null ) {
            return null;
        }

        TableInput tableInput = new TableInput();

        tableInput.setId( tableInputDTO.getId() );
        tableInput.setPosition( tableInputDTO.getPosition() );
        tableInput.setTeam( teamDTOToTeam( tableInputDTO.getTeam() ) );
        tableInput.setPlayedGames( tableInputDTO.getPlayedGames() );
        tableInput.setForm( tableInputDTO.getForm() );
        tableInput.setWon( tableInputDTO.getWon() );
        tableInput.setDraw( tableInputDTO.getDraw() );
        tableInput.setLost( tableInputDTO.getLost() );
        tableInput.setPoints( tableInputDTO.getPoints() );
        tableInput.setGoalsFor( tableInputDTO.getGoalsFor() );
        tableInput.setGoalsAgainst( tableInputDTO.getGoalsAgainst() );
        tableInput.setGoalDifference( tableInputDTO.getGoalDifference() );

        return tableInput;
    }

    default Team teamDTOToTeam(TeamDTO teamDTO) {
        if ( teamDTO == null ) {
            return null;
        }

        Team team = new Team();

        team.setId( teamDTO.getId() );
        team.setArea( areaDTOToArea( teamDTO.getArea() ) );
        team.setName( teamDTO.getName() );
        team.setShortName( teamDTO.getShortName() );
        team.setTla( teamDTO.getTla() );
        team.setCrestUrl( teamDTO.getCrestUrl() );
        team.setAddress( teamDTO.getAddress() );
        team.setPhone( teamDTO.getPhone() );
        team.setWebsite( teamDTO.getWebsite() );
        team.setEmail( teamDTO.getEmail() );
        team.setFounded( teamDTO.getFounded() );
        team.setClubColors( teamDTO.getClubColors() );
        team.setVenue( teamDTO.getVenue() );
        team.setLastUpdated( teamDTO.getLastUpdated() );

        return team;
    }

    default Area areaDTOToArea(AreaDTO areaDTO) {
        if ( areaDTO == null ) {
            return null;
        }

        Area area = new Area();

        area.setId( areaDTO.getId() );
        area.setName( areaDTO.getName() );
        area.setCountryCode( areaDTO.getCountryCode() );
        area.setEnsignUrl( areaDTO.getEnsignUrl() );

        return area;
    }


}
