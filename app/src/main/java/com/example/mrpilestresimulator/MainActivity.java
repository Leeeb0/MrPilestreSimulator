package com.example.mrpilestresimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup sleepQualityGroup;
    private MrPilestre joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sleepQualityGroup = findViewById(R.id.sleepQualityGroup);
        Button btnNext = findViewById(R.id.btnNext);

        joueur = new MrPilestre();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = sleepQualityGroup.getCheckedRadioButtonId();

                // 1. Mise à jour des stats
                if (selectedId == R.id.rbVeryGood) {
                    joueur.setEnergie(joueur.getEnergie() + 15);
                } else if (selectedId == R.id.rbBad) {
                    joueur.setEnergie(joueur.getEnergie() - 10);
                    joueur.setSante(joueur.getSante() - 5);
                } else if (selectedId == R.id.rbInsomnia) {
                    joueur.setEnergie(joueur.getEnergie() - 20);
                    joueur.setBonheur(joueur.getBonheur() - 10);
                } else {
                    // 2. Contrôle d'erreur
                    Toast.makeText(MainActivity.this, "Choisis une option avant de continuer", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 3. Toast de débogage
                String message = "Énergie : " + joueur.getEnergie() +
                        "\nSanté : " + joueur.getSante() +
                        "\nBonheur : " + joueur.getBonheur();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                // 4. Navigation vers la prochaine activité
                Intent intent = new Intent(MainActivity.this, SceneReveilActivity.class);
                intent.putExtra("JOUEUR_STATS", joueur);
                startActivity(intent);
            }
        });
    }
}