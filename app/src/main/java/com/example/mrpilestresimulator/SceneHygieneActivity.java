package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SceneHygieneActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private RadioGroup hygieneGroup;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (qui utilise ConstraintLayout)
        setContentView(R.layout.activity_scene_hygiene);

        // 2. Récupérer l'objet joueur transmis par SceneReveilActivity
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");

        // Sécurité pour éviter un crash
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets du layout XML
        hygieneGroup = findViewById(R.id.hygieneGroup);
        btnNext = findViewById(R.id.btnNextSceneHygiene);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = hygieneGroup.getCheckedRadioButtonId();

                // 5. Appliquer la logique du jeu (selon votre plan "android (3).pdf")
                if (selectedId == R.id.rbShowerFast) { // Option: Douche rapide
                    joueur.setEnergie(joueur.getEnergie() + 5);
                    joueur.setSante(joueur.getSante() + 2);
                } else if (selectedId == R.id.rbShowerCold) { // Option: Douche froide
                    joueur.setEnergie(joueur.getEnergie() + 10);
                    joueur.setBonheur(joueur.getBonheur() - 5);
                } else if (selectedId == R.id.rbNoShower) { // Option: Pas de douche
                    joueur.setSante(joueur.getSante() - 10);
                    joueur.setBonheur(joueur.getBonheur() - 5);
                } else {
                    // 6. Contrôle d'erreur (requis par le projet)
                    Toast.makeText(SceneHygieneActivity.this, "Un peu d'hygiène s'il vous plaît !", Toast.LENGTH_SHORT).show();
                    return; // On arrête le clic ici
                }

                // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
                // (Scène 4: Notification. Vous créerez "SceneNotificationActivity.java" ensuite)
                Intent intent = new Intent(SceneHygieneActivity.this, SceneNotificationActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 4
                startActivity(intent);
            }
        });
    }
}