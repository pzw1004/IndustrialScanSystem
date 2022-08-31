package com.industrialscansystem.Config;

import com.industrialscansystem.Bean.Requisition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequiredTypes {
    private List<Requisition> distinctTestingRate;
    private List<Requisition> distinctBevelForm;
    private List<Requisition> distinctFilmtype;
    private List<Requisition> distinctTransillumination;
    private List<Requisition> distinctFocusSize;
    private List<Requisition> distinctSensitizationMethod;
    private List<Requisition> distinctFilmProcessingMethod;

    public RequiredTypes() {
        distinctTestingRate = dealDistinctTestingRate();
        distinctBevelForm = dealDistinctBevelForm();
        distinctFilmtype = dealDistinctFilmtype();
        distinctTransillumination = dealDistinctTransillumination();
        distinctFocusSize = dealDistinctFocusSize();
        distinctSensitizationMethod = dealDistinctSensitizationMethod();
        distinctFilmProcessingMethod = dealDistinctFilmProcessingMethod();
    }

    public List<Requisition> getDistinctTestingRate() {
        return distinctTestingRate;
    }

    public void setDistinctTestingRate(List<Requisition> distinctTestingRate) {
        this.distinctTestingRate = distinctTestingRate;
    }

    public List<Requisition> getDistinctBevelForm() {
        return distinctBevelForm;
    }

    public void setDistinctBevelForm(List<Requisition> distinctBevelForm) {
        this.distinctBevelForm = distinctBevelForm;
    }

    public List<Requisition> getDistinctFilmtype() {
        return distinctFilmtype;
    }

    public void setDistinctFilmtype(List<Requisition> distinctFilmtype) {
        this.distinctFilmtype = distinctFilmtype;
    }

    public List<Requisition> getDistinctTransillumination() {
        return distinctTransillumination;
    }

    public void setDistinctTransillumination(List<Requisition> distinctTransillumination) {
        this.distinctTransillumination = distinctTransillumination;
    }

    public List<Requisition> getDistinctFocusSize() {
        return distinctFocusSize;
    }

    public void setDistinctFocusSize(List<Requisition> distinctFocusSize) {
        this.distinctFocusSize = distinctFocusSize;
    }

    public List<Requisition> getDistinctSensitizationMethod() {
        return distinctSensitizationMethod;
    }

    public void setDistinctSensitizationMethod(List<Requisition> distinctSensitizationMethod) {
        this.distinctSensitizationMethod = distinctSensitizationMethod;
    }

    public List<Requisition> getDistinctFilmProcessingMethod() {
        return distinctFilmProcessingMethod;
    }

    public void setDistinctFilmProcessingMethod(List<Requisition> distinctFilmProcessingMethod) {
        this.distinctFilmProcessingMethod = distinctFilmProcessingMethod;
    }

    public List<Requisition> dealDistinctTestingRate() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        Requisition req3 = new Requisition();
        req1.setRequisition_testing_rate("20");
        req2.setRequisition_testing_rate("50");
        req3.setRequisition_testing_rate("100");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        return list;
    }

    public List<Requisition> dealDistinctBevelForm() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        Requisition req3 = new Requisition();
        Requisition req4 = new Requisition();
        Requisition req5 = new Requisition();
        req1.setRequisition_bevel_form("V");
        req2.setRequisition_bevel_form("U");
        req3.setRequisition_bevel_form("I");
        req4.setRequisition_bevel_form("X");
        req5.setRequisition_bevel_form("Y");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        list.add(req4);
        list.add(req5);
        return list;
    }

    public List<Requisition> dealDistinctFilmtype() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        req1.setRequisition_filmtype("AgfaD4");
        req2.setRequisition_filmtype("AgfaD7");
        list.add(req1);
        list.add(req2);
        return list;
    }

    public List<Requisition> dealDistinctFocusSize() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        req1.setRequisition_focus_size("3x3");
        list.add(req1);
        return list;
    }

    public List<Requisition> dealDistinctSensitizationMethod() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        req1.setRequisition_sensitization_method("铅增感");
        req2.setRequisition_sensitization_method("其它");
        list.add(req1);
        list.add(req2);
        return list;
    }

    public List<Requisition> dealDistinctFilmProcessingMethod() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        req1.setRequisition_film_processing_method("机洗");
        req2.setRequisition_film_processing_method("手洗");
        list.add(req1);
        list.add(req2);
        return list;
    }

    public List<Requisition> dealDistinctTransillumination() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        Requisition req3 = new Requisition();
        req1.setRequisition_transillumination("单壁透照");
        req2.setRequisition_transillumination("双壁双影");
        req3.setRequisition_transillumination("双壁单影");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        return list;
    }
}
