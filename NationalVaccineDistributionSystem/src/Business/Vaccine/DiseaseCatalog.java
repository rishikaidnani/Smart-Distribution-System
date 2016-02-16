/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Vaccine;

import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class DiseaseCatalog {

    private ArrayList<Disease> diseaseList;

    public DiseaseCatalog() {
        this.diseaseList = new ArrayList<>();
    }

    public ArrayList<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(ArrayList<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public Disease addAndCreateDisease(String diseaseName) {
        for (Disease d : diseaseList) {
            if (d.getDiseaseName().equalsIgnoreCase(diseaseName)) {
                return null;
            }
        }

        Disease disease = new Disease();
        diseaseList.add(disease);
        disease.setDiseaseName(diseaseName);
        return disease;
    }

    public void removeDisease(Disease disease) {
        diseaseList.remove(disease);
    }
}
