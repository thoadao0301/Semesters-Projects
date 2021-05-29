/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public interface I_SubjectList {
    public int searchSubject(String subjID);
    public void addSubject();
    public void updateSubject(int pos);
    public void deleteSubject(int pos);
}
