package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox; // <-- On utilise des CheckBox
import android.widget.Toast;

public class SceneCorveeActivity extends AppCompatActivity {

    private MrPilestre joueur;
    private CheckBox cbVaisselle, cbLinge, cbIgnorer;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Lier le fichier XML
        setContentView(R.layout.activity_scene_corvee);

        // 2. Récupérer l'objet joueur
        joueur = getIntent().getParcelableExtra("JOUEUR_STATS");
        if (joueur == null) {
            joueur = new MrPilestre();
        }

        // 3. Lier les widgets (vus en TP [cite: 11483, 11617])
        cbVaisselle = findViewById(R.id.cbVaisselle);
        cbLinge = findViewById(R.id.cbLinge);
        cbIgnorer = findViewById(R.id.cbIgnorer);
        btnNext = findViewById(R.id.btnNextSceneCorvee);

        // 4. Définir l'action du bouton "Suivant"
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5. Récupérer l'état des CheckBox
                boolean vaisselleChecked = cbVaisselle.isChecked();
                boolean lingeChecked = cbLinge.isChecked();
                boolean ignorerChecked = cbIgnorer.isChecked();

                // 6. Contrôle d'erreur (requis par le projet )
                // Si rien n'est coché, on affiche une erreur et on arrête.
                if (!vaisselleChecked && !lingeChecked && !ignorerChecked) {
                    Toast.makeText(SceneCorveeActivity.this, "Tu dois faire un choix !", Toast.LENGTH_SHORT).show();
                    return; // On arrête le clic ici
                }

                // 7. Appliquer la logique du jeu
                if (ignorerChecked) {
                    // Si "Ignorer" est coché, il annule le reste
                    joueur.setBonheur(joueur.getBonheur() + 5);
                    joueur.setDiscipline(joueur.getDiscipline() - 10);
                } else {
                    // Sinon, on applique les effets (qui peuvent se cumuler)
                    if (vaisselleChecked) {
                        joueur.setDiscipline(joueur.getDiscipline() + 5);
                        joueur.setBonheur(joueur.getBonheur() - 5);
                    }
                    if (lingeChecked) {
                        joueur.setDiscipline(joueur.getDiscipline() + 5);
                        joueur.setEnergie(joueur.getEnergie() - 5);
                    }
                }

                // 8. PRÉPARER L'ACTIVITÉ SUIVANTE
                // (Scène 13: Jeu de hasard. Vous créerez "SceneHasardActivity.java" ensuite)
                Intent intent = new Intent(SceneCorveeActivity.this, SceneHasardActivity.class);

                // On attache l'objet "joueur" MIS À JOUR
                intent.putExtra("JOUEUR_STATS", joueur);

                // On démarre la scène 13
                startActivity(intent);
            }
        });
    }
}