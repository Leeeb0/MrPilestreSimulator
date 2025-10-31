package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView; // <-- On a besoin d'un TextView
import java.util.Random; // <-- On a besoin de "Random" pour l'aléatoire

public class SceneHasardActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private TextView tvResultatHasard;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_hasard);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets
        tvResultatHasard = findViewById(R.id.tvResultatHasard);
        btnNext = findViewById(R.id.btnNextSceneHasard);

        // 4. Logique de l'événement aléatoire
        Random random = new Random();
        // nextBoolean() donne 50% de chance de gagner (true) ou perdre (false)
        boolean aGagne = random.nextBoolean();

        if (aGagne) {
            // Appliquer le gain
            joueur.setArgent(joueur.getArgent() + 10);
            // Mettre à jour le texte
            tvResultatHasard.setText("Incroyable ! C'est votre jour de chance, vous gagnez !\n(Argent +10)");
        } else {
            // Appliquer la perte
            joueur.setArgent(joueur.getArgent() - 2);
            // Mettre à jour le texte
            tvResultatHasard.setText("Dommage... Vous perdez. Ce n'est pas grand-chose.\n(Argent -2)");
        }

        // 5. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6. PRÉPARER L'ACTIVITÉ SUIVANTE
                Intent intent = new Intent(SceneHasardActivity.this, SceneSoirActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 14
                startActivity(intent);
            }
        });
    }
}