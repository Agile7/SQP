package com.agileseven.codereview.client;

import com.agileseven.codereview.client.DTO.UserDTO;

import java.util.List;

/**
 * @author Mahmoud AL NAJAR
 */
public class UserDTOWithXPGains extends UserDTO {

    private List<UserXpSingleInterval> xpGainIntervalsList;

    public UserDTOWithXPGains() {
    }

    public UserDTOWithXPGains(List<UserXpSingleInterval> xpGainIntervalsList) {
        this.xpGainIntervalsList = xpGainIntervalsList;
    }

    public UserDTOWithXPGains(int userId, String firstName, String lastName, String email, String photoPath, int positionId, int projectId, List<UserXpSingleInterval> xpGainIntervalsList) {
        super(userId, firstName, lastName, email, photoPath, positionId, projectId);
        this.xpGainIntervalsList = xpGainIntervalsList;
    }

    public List<UserXpSingleInterval> getXpGainIntervalsList() {
        return xpGainIntervalsList;
    }

    public void setXpGainIntervalsList(List<UserXpSingleInterval> xpGainIntervalsList) {
        this.xpGainIntervalsList = xpGainIntervalsList;
    }

}