package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton; // <-- MODIFICATION : On utilise des ImageButton
import android.widget.Toast;

public class SceneNotificationActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private ImageButton btnInvitation, btnMauvaiseNouvelle, btnRien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML (qui utilise ConstraintLayout)
        setContentView(R.layout.activity_scene_notification);

        // 2. Récupérer l'objet joueur transmis par SceneHygieneActivity
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");

        // Sécurité pour éviter un crash
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets du layout XML
        btnInvitation = findViewById(R.id.btnInvitation);
        btnMauvaiseNouvelle = findViewById(R.id.btnMauvaiseNouvelle);
        btnRien = findViewById(R.id.btnRien);

        // 4. Définir l'action pour CHAQUE bouton

        // Clic sur "Invitation"
        btnInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Bonheur +5)
                joueur.setBonheur(joueur.getBonheur() + 5);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });

        // Clic sur "Mauvaise nouvelle"
        btnMauvaiseNouvelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Bonheur -5)
                joueur.setBonheur(joueur.getBonheur() - 5);

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });

        // Clic sur "Rien"
        btnRien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5. Appliquer la logique du jeu (Aucun effet)
                // On ne fait rien

                // 6. Passer à la scène suivante
                passerALaSceneSuivante();
            }
        });
    }

    /**
     * Méthode pour préparer et lancer l'activité suivante.
     * C'est la même logique que dans les scènes précédentes.
     */
    private void passerALaSceneSuivante() {
        // 7. PRÉPARER L'ACTIVITÉ SUIVANTE
        // (Scène 5: Petit-déjeuner. Vous créerez "ScenePetitDejeunerActivity.java" ensuite)
        Intent intent = new Intent(SceneNotificationActivity.this, ScenePetitDejeunerActivity.class);

        // On attache l'objet "joueur" MIS À JOUR
        intent.putExtra("JOUEUR_STATS", joueur);

        // On démarre la scène 5
        startActivity(intent);
    }
}