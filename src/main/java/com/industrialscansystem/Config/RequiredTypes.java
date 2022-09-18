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
    private List<Requisition> distinctQualificationLevel;
    private List<Requisition> distinctTestingInstrument;
    private List<Requisition> distinctWeldingMethod;
    private List<Requisition> distinctIntensifyScreenFront;
    private List<Requisition> distinctIntensifyScreenMiddle;
    private List<Requisition> distinctIntensifyScreenBehind;

    public RequiredTypes() {
        distinctTestingRate = dealDistinctTestingRate();
        distinctBevelForm = dealDistinctBevelForm();
        distinctFilmtype = dealDistinctFilmtype();
        distinctTransillumination = dealDistinctTransillumination();
        distinctFocusSize = dealDistinctFocusSize();
        distinctSensitizationMethod = dealDistinctSensitizationMethod();
        distinctFilmProcessingMethod = dealDistinctFilmProcessingMethod();
        distinctQualificationLevel = dealDistinctQualificationLevel();
        distinctTestingInstrument = dealDistinctTestingInstrument();
        distinctWeldingMethod = dealDistinctWeldingMethod();
        distinctIntensifyScreenFront = dealDistinctIntensifyScreenFront();
        distinctIntensifyScreenMiddle = dealDistinctIntensifyScreenMiddle();
        distinctIntensifyScreenBehind = dealDistinctIntensifyScreenBehind();
    }

    public List<Requisition> getDistinctQualificationLevel() {
        return distinctQualificationLevel;
    }

    public void setDistinctQualificationLevel(List<Requisition> distinctQualificationLevel) {
        this.distinctQualificationLevel = distinctQualificationLevel;
    }

    public List<Requisition> getDistinctTestingInstrument() {
        return distinctTestingInstrument;
    }

    public void setDistinctTestingInstrument(List<Requisition> distinctTestingInstrument) {
        this.distinctTestingInstrument = distinctTestingInstrument;
    }

    public List<Requisition> getDistinctWeldingMethod() {
        return distinctWeldingMethod;
    }

    public void setDistinctWeldingMethod(List<Requisition> distinctWeldingMethod) {
        this.distinctWeldingMethod = distinctWeldingMethod;
    }

    public List<Requisition> getDistinctIntensifyScreenFront() {
        return distinctIntensifyScreenFront;
    }

    public void setDistinctIntensifyScreenFront(List<Requisition> distinctIntensifyScreenFront) {
        this.distinctIntensifyScreenFront = distinctIntensifyScreenFront;
    }

    public List<Requisition> getDistinctIntensifyScreenMiddle() {
        return distinctIntensifyScreenMiddle;
    }

    public void setDistinctIntensifyScreenMiddle(List<Requisition> distinctIntensifyScreenMiddle) {
        this.distinctIntensifyScreenMiddle = distinctIntensifyScreenMiddle;
    }

    public List<Requisition> getDistinctIntensifyScreenBehind() {
        return distinctIntensifyScreenBehind;
    }

    public void setDistinctIntensifyScreenBehind(List<Requisition> distinctIntensifyScreenBehind) {
        this.distinctIntensifyScreenBehind = distinctIntensifyScreenBehind;
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
        Requisition req2 = new Requisition();
        req1.setRequisition_focus_size("3x3");
        req2.setRequisition_focus_size("2x2");
        list.add(req1);
        list.add(req2);
        return list;
    }

    public List<Requisition> dealDistinctSensitizationMethod() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        Requisition req3 = new Requisition();
        Requisition req4 = new Requisition();
        req1.setRequisition_sensitization_method("铅增感");
        req2.setRequisition_sensitization_method("铜增感");
        req3.setRequisition_sensitization_method("钢增感");
        req4.setRequisition_sensitization_method("其它");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        list.add(req4);
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

    public List<Requisition> dealDistinctQualificationLevel() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        Requisition req3 = new Requisition();
        Requisition req4 = new Requisition();
        Requisition req5 = new Requisition();
        Requisition req6 = new Requisition();
        req1.setRequisition_qualificationlevel("1级");
        req2.setRequisition_qualificationlevel("2级");
        req3.setRequisition_qualificationlevel("I级");
        req4.setRequisition_qualificationlevel("II级");
        req5.setRequisition_qualificationlevel("合格");
        req6.setRequisition_qualificationlevel("不合格");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        list.add(req4);
        list.add(req5);
        list.add(req6);
        return list;
    }

    public List<Requisition> dealDistinctTestingInstrument() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        Requisition req3 = new Requisition();
        req1.setRequisition_testing_instrument("X机");
        req2.setRequisition_testing_instrument("γ射线源");
        req3.setRequisition_testing_instrument("加速器");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        return list;
    }

    public List<Requisition> dealDistinctWeldingMethod() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        Requisition req3 = new Requisition();
        Requisition req4 = new Requisition();
        Requisition req5 = new Requisition();
        req1.setRequisition_weldingmethod("手工焊");
        req2.setRequisition_weldingmethod("自动焊");
        req3.setRequisition_weldingmethod("气体保护焊");
        req4.setRequisition_weldingmethod("氩弧焊");
        req5.setRequisition_weldingmethod("埋弧焊");
        list.add(req1);
        list.add(req2);
        list.add(req3);
        list.add(req4);
        list.add(req5);
        return list;
    }

    public List<Requisition> dealDistinctIntensifyScreenFront() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        req1.setRequisition_intensifyscreen_front("0.1");
        req2.setRequisition_intensifyscreen_front("0.16");
        list.add(req1);
        list.add(req2);
        return list;
    }

    public List<Requisition> dealDistinctIntensifyScreenMiddle() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        req1.setRequisition_intensifyscreen_middle("0.1");
        req2.setRequisition_intensifyscreen_middle("0.16");
        list.add(req1);
        list.add(req2);
        return list;
    }

    public List<Requisition> dealDistinctIntensifyScreenBehind() {
        List<Requisition> list = new ArrayList<>();
        Requisition req1 = new Requisition();
        Requisition req2 = new Requisition();
        req1.setRequisition_intensifyscreen_behind("0.1");
        req2.setRequisition_intensifyscreen_behind("0.16");
        list.add(req1);
        list.add(req2);
        return list;
    }
}
