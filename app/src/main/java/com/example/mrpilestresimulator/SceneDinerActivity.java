package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner; // <-- On utilise un Spinner
import android.widget.Toast;

public class SceneDinerActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private Spinner spinnerDiner;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (ConstraintLayout)
        setContentView(R.layout.activity_scene_diner);

        // 2. Récupérer l'objet joueur (logique TP PokeStat)
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets (logique TP ConvTemp)
        spinnerDiner = findViewById(R.id.spinnerDiner);
        btnNext = findViewById(R.id.btnNextSceneDiner);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5. Récupérer le choix du Spinner
                // 0="Pizza", 1="Salade", 2="Restaurant"
                int position = spinnerDiner.getSelectedItemPosition();

                // 6. Appliquer la logique du jeu (selon votre plan)
                if (position == 0) { // Option: Pizza
                    joueur.setSante(joueur.getSante() - 5);
                    joueur.setBonheur(joueur.getBonheur() + 5);
                    joueur.setArgent(joueur.getArgent() - 8);
                } else if (position == 1) { // Option: Salade
                    joueur.setSante(joueur.getSante() + 10);
                    joueur.setEnergie(joueur.getEnergie() + 5);
                    joueur.setArgent(joueur.getArgent() - 5);
                } else if (position == 2) { // Option: Restaurant
                    joueur.setBonheur(joueur.getBonheur() + 10);
                    joueur.setSante(joueur.getSante() + 5);
                    joueur.setArgent(joueur.getArgent() - 20);
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                // (Scène 16: Prépa Sommeil. Vous créerez "SceneSommeilActivity.java" ensuite)
                Intent intent = new Intent(SceneDinerActivity.this, SceneSommeilActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 16
                startActivity(intent);
            }
        });
    }
}