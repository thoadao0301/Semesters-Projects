
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class Subject {
    private String subjectID, subjName;
    private int credit;
    private boolean canDelete = true;

    public Subject() {
        this.subjectID = "";
        this.subjName = "";
        this.credit = 0;
    }

    public Subject(String subjectID, String subjName, int credit) {
        this.subjectID = subjectID;
        this.subjName = subjName;
        this.credit = credit;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean CanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    
    public String toString()
    {
        return (" " + this.subjectID + "\t" + this.subjName + "\t" + this.credit);
    }
    
    
}
