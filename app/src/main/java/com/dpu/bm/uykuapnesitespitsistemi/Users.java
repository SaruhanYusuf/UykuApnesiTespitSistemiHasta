package com.dpu.bm.uykuapnesitespitsistemi;


import androidx.annotation.NonNull;

public class Users {

    private int _hastaId;
    private String _diagnosis;
    private String _isim,_soyisim;
    private String _oxygen,_heartBeat,_tempt;
    private final String OXYGEN="O²", HEART_BEAT="BPM", TEMPERATURE="C°";

    public Users() {
    }

    public Users(int _hastaId, String _diagnosis, String _isim, String _soyisim, String _oxygen, String _heartBeat, String _tempt) {
        this._hastaId = _hastaId;
        this._diagnosis = _diagnosis;
        this._isim = _isim;
        this._soyisim = _soyisim;
        this._oxygen = _oxygen;
        this._heartBeat = _heartBeat;
        this._tempt = _tempt;
    }

    public int get_hastaId() {
        return _hastaId;
    }

    public void set_hastaId(int _hastaId) {
        this._hastaId = _hastaId;
    }

    public String get_diagnosis() {
        return _diagnosis;
    }

    public void set_diagnosis(String _diagnosis) {
        this._diagnosis = _diagnosis;
    }

    public String get_isim() {
        return _isim;
    }

    public void set_isim(String _isim) {
        this._isim = _isim;
    }

    public String get_soyisim() {
        return _soyisim;
    }

    public void set_soyisim(String _soyisim) {
        this._soyisim = _soyisim;
    }

    public String get_oxygen() {
        return _oxygen;
    }

    public void set_oxygen(String _oxygen) {
        this._oxygen = _oxygen;
    }

    public String get_heartBeat() {
        return _heartBeat;
    }

    public void set_heartBeat(String _heartBeat) {
        this._heartBeat = _heartBeat;
    }

    public String get_tempt() {
        return _tempt;
    }

    public void set_tempt(String _tempt) {
        this._tempt = _tempt;
    }

    public String getOXYGEN() {
        return OXYGEN;
    }

    public String getHEART_BEAT() {
        return HEART_BEAT;
    }

    public String getTEMPERATURE() {
        return TEMPERATURE;
    }

    @NonNull
    @Override
    public String toString() {
        return "Hasta Adı:" + get_isim() + "Kalp Atışı:" + get_heartBeat() + getHEART_BEAT();
    }
}
