package com.example.taammar.model;

import java.io.Serializable;

public class MappingGizi implements Serializable {

    int Number;
    String Gender;
    String MinUsia;
    String MaxUsia;
    String VitA;
    String VITD;
    String VITE;
    String VitK;
    String VitB1;
    String VitB2;
    String VitB3;
    String VitB5;
    String VitB6;
    String VitH;
    String VitB9;
    String VitB12;
    String VitC;

    public MappingGizi(){

    }

    public MappingGizi(int number, String gender, String minUsia, String maxUsia,
                       String vitA, String VITD, String VITE, String vitK, String vitB1,
                       String vitB2, String vitB3, String vitB5, String vitB6, String vitH,
                       String vitB9, String vitB12, String vitC) {
        Number = number;
        Gender = gender;
        MinUsia = minUsia;
        MaxUsia = maxUsia;
        VitA = vitA;
        this.VITD = VITD;
        this.VITE = VITE;
        VitK = vitK;
        VitB1 = vitB1;
        VitB2 = vitB2;
        VitB3 = vitB3;
        VitB5 = vitB5;
        VitB6 = vitB6;
        VitH = vitH;
        VitB9 = vitB9;
        VitB12 = vitB12;
        VitC = vitC;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMinUsia() {
        return MinUsia;
    }

    public void setMinUsia(String minUsia) {
        MinUsia = minUsia;
    }

    public String getMaxUsia() {
        return MaxUsia;
    }

    public void setMaxUsia(String maxUsia) {
        MaxUsia = maxUsia;
    }

    public String getVitA() {
        return VitA;
    }

    public void setVitA(String vitA) {
        VitA = vitA;
    }

    public String getVITD() {
        return VITD;
    }

    public void setVITD(String VITD) {
        this.VITD = VITD;
    }

    public String getVITE() {
        return VITE;
    }

    public void setVITE(String VITE) {
        this.VITE = VITE;
    }

    public String getVitK() {
        return VitK;
    }

    public void setVitK(String vitK) {
        VitK = vitK;
    }

    public String getVitB1() {
        return VitB1;
    }

    public void setVitB1(String vitB1) {
        VitB1 = vitB1;
    }

    public String getVitB2() {
        return VitB2;
    }

    public void setVitB2(String vitB2) {
        VitB2 = vitB2;
    }

    public String getVitB3() {
        return VitB3;
    }

    public void setVitB3(String vitB3) {
        VitB3 = vitB3;
    }

    public String getVitB5() {
        return VitB5;
    }

    public void setVitB5(String vitB5) {
        VitB5 = vitB5;
    }

    public String getVitB6() {
        return VitB6;
    }

    public void setVitB6(String vitB6) {
        VitB6 = vitB6;
    }

    public String getVitH() {
        return VitH;
    }

    public void setVitH(String vitH) {
        VitH = vitH;
    }

    public String getVitB9() {
        return VitB9;
    }

    public void setVitB9(String vitB9) {
        VitB9 = vitB9;
    }

    public String getVitB12() {
        return VitB12;
    }

    public void setVitB12(String vitB12) {
        VitB12 = vitB12;
    }

    public String getVitC() {
        return VitC;
    }

    public void setVitC(String vitC) {
        VitC = vitC;
    }


}
