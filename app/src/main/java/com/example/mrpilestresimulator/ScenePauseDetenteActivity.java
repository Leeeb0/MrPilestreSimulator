package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ScenePauseDetenteActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private Spinner spinnerPauseDetente;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_pause_detente);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        spinnerPauseDetente = findViewById(R.id.spinnerPauseDetente);
        btnNext = findViewById(R.id.btnNextScenePause);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5. Récupérer le choix du Spinner
                int position = spinnerPauseDetente.getSelectedItemPosition();

                // 6. Appliquer la logique du jeu
                if (position == 0) { // Option: Sieste
                    joueur.setEnergie(joueur.getEnergie() + 10);
                } else if (position == 1) { // Option: Réseaux sociaux
                    joueur.setBonheur(joueur.getBonheur() + 5);
                    joueur.setEnergie(joueur.getEnergie() - 5);
                } else if (position == 2) { // Option: Lecture
                    joueur.setCompetences(joueur.getCompetences() + 5);
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                Intent intent = new Intent(ScenePauseDetenteActivity.this, SceneMidiActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 10
                startActivity(intent);
            }
        });
    }
}