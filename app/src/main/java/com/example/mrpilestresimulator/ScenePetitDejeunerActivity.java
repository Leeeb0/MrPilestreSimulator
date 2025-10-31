package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ScenePetitDejeunerActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private Spinner spinnerPetitDejeuner;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_petit_dejeuner);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        spinnerPetitDejeuner = findViewById(R.id.spinnerPetitDejeuner);
        btnNext = findViewById(R.id.btnNextScenePetitDejeuner);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5. Récupérer le choix du Spinner
                int position = spinnerPetitDejeuner.getSelectedItemPosition();

                // 6. Appliquer la logique du jeu
                if (position == 0) { // Option: Rien
                    joueur.setEnergie(joueur.getEnergie() - 10);
                    joueur.setSante(joueur.getSante() - 5);
                } else if (position == 1) { // Option: Café/croissant
                    joueur.setEnergie(joueur.getEnergie() + 5);
                    joueur.setSante(joueur.getSante() - 2);
                    joueur.setArgent(joueur.getArgent() - 5);
                } else if (position == 2) { // Option: Repas équilibré
                    joueur.setEnergie(joueur.getEnergie() + 10);
                    joueur.setSante(joueur.getSante() + 10);
                    joueur.setArgent(joueur.getArgent() - 10);
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                Intent intent = new Intent(ScenePetitDejeunerActivity.this, SceneTransportActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 6
                startActivity(intent);
            }
        });
    }
}