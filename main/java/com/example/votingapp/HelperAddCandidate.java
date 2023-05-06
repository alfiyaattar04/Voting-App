package com.example.votingapp;

public class HelperAddCandidate {

    String CandidateName,CandidateEmail,CandidateMobNo,CandidateAge,Candidateaddress,Candidateuserid,CandidateVote,CandidateuserIdused;

    public HelperAddCandidate()
    {

    }

    public HelperAddCandidate(String candidateName, String candidateEmail, String candidateMobNo, String candidateAge, String candidateaddress, String candidateuserid, String candidateVote, String candidateuserIdused) {
        CandidateName = candidateName;
        CandidateEmail = candidateEmail;
        CandidateMobNo = candidateMobNo;
        CandidateAge = candidateAge;
        Candidateaddress = candidateaddress;
        Candidateuserid = candidateuserid;
        CandidateVote = candidateVote;
        CandidateuserIdused = candidateuserIdused;
    }

    public String getCandidateName() {
        return CandidateName;
    }

    public void setCandidateName(String candidateName) {
        CandidateName = candidateName;
    }

    public String getCandidateEmail() {
        return CandidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        CandidateEmail = candidateEmail;
    }

    public String getCandidateMobNo() {
        return CandidateMobNo;
    }

    public void setCandidateMobNo(String candidateMobNo) {
        CandidateMobNo = candidateMobNo;
    }

    public String getCandidateAge() {
        return CandidateAge;
    }

    public void setCandidateAge(String candidateAge) {
        CandidateAge = candidateAge;
    }

    public String getCandidateaddress() {
        return Candidateaddress;
    }

    public void setCandidateaddress(String candidateaddress) {
        Candidateaddress = candidateaddress;
    }

    public String getCandidateuserid() {
        return Candidateuserid;
    }

    public void setCandidateuserid(String candidateuserid) {
        Candidateuserid = candidateuserid;
    }

    public String getCandidateVote() {
        return CandidateVote;
    }

    public void setCandidateVote(String candidateVote) {
        CandidateVote = candidateVote;
    }

    public String getCandidateuserIdused() {
        return CandidateuserIdused;
    }

    public void setCandidateuserIdused(String candidateuserIdused) {
        CandidateuserIdused = candidateuserIdused;
    }
}
