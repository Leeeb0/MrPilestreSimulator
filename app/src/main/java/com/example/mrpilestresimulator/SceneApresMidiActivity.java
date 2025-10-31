package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class SceneApresMidiActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private SeekBar seekBarApresMidi;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_apres_midi);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        seekBarApresMidi = findViewById(R.id.seekBarApresMidi);
        btnNext = findViewById(R.id.btnNextSceneApresMidi);

        // 4. Définir l'action du bouton "Valider"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5. Récupérer la valeur du SeekBar
                int progression = seekBarApresMidi.getProgress();

                // Si la barre est plutôt à gauche (< 50) -> Travailler
                if (progression < 50) {
                    joueur.setCompetences(joueur.getCompetences() + 10);
                    joueur.setBonheur(joueur.getBonheur() - 5);
                } else {
                    // Si la barre est plutôt à droite (>= 50) -> Sortir
                    joueur.setBonheur(joueur.getBonheur() + 10);
                    joueur.setCompetences(joueur.getCompetences() - 5);
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                Intent intent = new Intent(SceneApresMidiActivity.this, SceneCorveeActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 12
                startActivity(intent);
            }
        });
    }
}