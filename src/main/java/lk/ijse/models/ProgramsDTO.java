package lk.ijse.models;

import java.io.Serializable;

public class ProgramsDTO implements Serializable {
    private String programID;
    private String prName;
    private String duration;
    private Double prFee;

    public ProgramsDTO() {

    }

    public ProgramsDTO(String programID, String prName, String duration, Double prFee) {
        this.programID = programID;
        this.prName = prName;
        this.duration = duration;
        this.prFee = prFee;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrFee() {
        return prFee;
    }

    public void setPrFee(Double prFee) {
        this.prFee = prFee;
    }

    @Override
    public String toString() {
        return "ProgramsDTO{" +
                "programID='" + programID + '\'' +
                ", prName='" + prName + '\'' +
                ", duration='" + duration + '\'' +
                ", prFee=" + prFee +
                '}';
    }
}
