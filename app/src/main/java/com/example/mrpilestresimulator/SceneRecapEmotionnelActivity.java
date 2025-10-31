package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton; // <-- On utilise des ImageButton
import android.widget.Toast;

public class SceneRecapEmotionnelActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private ImageButton btnSatisfait, btnMitige, btnEpuise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (ConstraintLayout)
        setContentView(R.layout.activity_scene_recap_emotionnel);

        // 2. Récupérer l'objet joueur (logique TP PokeStat)
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets (logique TP ConvTemp)
        btnSatisfait = findViewById(R.id.btnSatisfait);
        btnMitige = findViewById(R.id.btnMitige);
        btnEpuise = findViewById(R.id.btnEpuise);

        // 4. Définir l'action pour "Satisfait"
        btnSatisfait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Bonheur +10) [cite: 9290]
                joueur.setBonheur(joueur.getBonheur() + 10);
                passerALaSceneDuBilan();
            }
        });

        // 4. Définir l'action pour "Mitigé"
        btnMitige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Bonheur +0) [cite: 9291]
                // Aucun changement de stat
                passerALaSceneDuBilan();
            }
        });

        // 4. Définir l'action pour "Épuisé"
        btnEpuise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Bonheur -10) [cite: 9292]
                joueur.setBonheur(joueur.getBonheur() - 10);
                passerALaSceneDuBilan();
            }
        });
    }

    /**
     * Méthode partagée pour lancer l'activité suivante (LE BILAN !)
     */
    private void passerALaSceneDuBilan() {
        // 6. PRÉPARER L'ACTIVITÉ FINALE (LE BILAN)
        // (Scène 18: Bilan. Vous créerez "BilanActivity.java" ensuite)
        Intent intent = new Intent(SceneRecapEmotionnelActivity.this, BilanActivity.class);

        // On attache l'objet "joueur" FINALISÉ
        intent.putExtra("JOUEUR_STATS", joueur);

        // On démarre la scène 18
        startActivity(intent);

        // Optionnel : on peut fermer cette activité
        // pour que l'utilisateur ne puisse pas revenir en arrière depuis le bilan
        // finish();
    }
}