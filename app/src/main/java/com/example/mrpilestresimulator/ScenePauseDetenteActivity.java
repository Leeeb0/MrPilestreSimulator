package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner; // <-- On utilise un Spinner
import android.widget.Toast;

public class ScenePauseDetenteActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private Spinner spinnerPauseDetente;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_pause_detente); [cite: 10]

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS"); [cite: 2]
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        spinnerPauseDetente = findViewById(R.id.spinnerPauseDetente); [cite: 8]
        btnNext = findViewById(R.id.btnNextScenePause); [cite: 8]

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5. Récupérer le choix du Spinner
                // 0 = "Sieste", 1 = "Réseaux sociaux", 2 = "Lecture"
                int position = spinnerPauseDetente.getSelectedItemPosition();

                // 6. Appliquer la logique du jeu (selon votre plan)
                if (position == 0) { // Option: Sieste
                    joueur.setEnergie(joueur.getEnergie() + 10);
                } else if (position == 1) { // Option: Réseaux sociaux
                    joueur.setBonheur(joueur.getBonheur() + 5);
                    joueur.setEnergie(joueur.getEnergie() - 5);
                } else if (position == 2) { // Option: Lecture
                    joueur.setCompetences(joueur.getCompetences() + 5);
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                // (Scène 10: Midi. Vous créerez "SceneMidiActivity.java" ensuite)
                Intent intent = new Intent(ScenePauseDetenteActivity.this, SceneMidiActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur); [cite: 2]

                // On démarre la scène 10
                startActivity(intent);
            }
        });
    }
}