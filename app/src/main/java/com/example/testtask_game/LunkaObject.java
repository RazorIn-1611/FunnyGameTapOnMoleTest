package com.example.testtask_game;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class LunkaObject {
    private boolean isActive;
    private ImageButton lunka;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ImageButton getLunka() {
        return lunka;
    }

    public void setLunka(ImageButton lunka) {
        this.lunka = lunka;
    }

    public LunkaObject(ImageButton lunka) {
        this.isActive = false;
        this.lunka = lunka;
    }
}
