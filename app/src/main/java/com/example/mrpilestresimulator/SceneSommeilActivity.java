package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SceneSommeilActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private RadioGroup sommeilGroup;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (ConstraintLayout)
        setContentView(R.layout.activity_scene_sommeil);

        // 2. Récupérer l'objet joueur (logique TP PokeStat)
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets (logique TP ConvTemp)
        sommeilGroup = findViewById(R.id.sommeilGroup);
        btnNext = findViewById(R.id.btnNextSceneSommeil);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = sommeilGroup.getCheckedRadioButtonId();

                // 5. Appliquer la logique du jeu (selon votre plan)
                if (selectedId == R.id.rbScroll) { // Option: Scroll téléphone
                    joueur.setEnergie(joueur.getEnergie() - 10);
                    joueur.setBonheur(joueur.getBonheur() + 5);
                } else if (selectedId == R.id.rbLecture) { // Option: Lecture calme
                    joueur.setEnergie(joueur.getEnergie() - 5);
                    joueur.setSante(joueur.getSante() + 5);
                } else if (selectedId == R.id.rbMeditation) { // Option: Méditation
                    joueur.setEnergie(joueur.getEnergie() + 5);
                    joueur.setSante(joueur.getSante() + 10);
                } else {
                    // 6. Contrôle d'erreur (requis par le projet)
                    Toast.makeText(SceneSommeilActivity.this, "Tu dois bien faire quelque chose avant de dormir !", Toast.LENGTH_SHORT).show();
                    return; // On arrête le clic ici
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                // (Scène 17: Récap émo. Vous créerez "SceneRecapEmotionnelActivity.java" ensuite)
                Intent intent = new Intent(SceneSommeilActivity.this, SceneRecapEmotionnelActivity.class);

                // On attache l'objet "joueur" MIS À JOUR (logique TP PokeStat)
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 17
                startActivity(intent);
            }
        });
    }
}