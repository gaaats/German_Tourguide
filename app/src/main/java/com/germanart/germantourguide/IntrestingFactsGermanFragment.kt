package com.germanart.germantourguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.germanart.germantourguide.databinding.FragmentIntrestingFactsGermanBinding
import com.google.android.material.snackbar.Snackbar

class IntrestingFactsGermanFragment : Fragment() {
    private var _binding: FragmentIntrestingFactsGermanBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    private val listFactsLogo = listOf(
        R.drawable.holstentor,
        R.drawable.beer,
        R.drawable.house,
        R.drawable.castle,
        R.drawable.germany_facts,
        R.drawable.coat,
        R.drawable.gate
    )

    private val listFactsText = listOf(
        "There are over 1,000 varieties of sausage in Germany. It’s safe to say that Germans love their sausage! Some kinds of sausage you’ll find in Germany are: \n" +
                "\n" +
                "Bratwurst\n" +
                "Blutwurst\n" +
                "Weisswurst \n" +
                "Knackwurst\n" +
                "Currywurst",
        "Müller is the most popular surname in Germany. There are over 900,000 people in Germany who share this last name! A few other popular last names in Germany are: \n" +
                "\n" +
                "Schmidt\n" +
                "Schneider\n" +
                "Fischer \n" +
                "Weber \n" +
                "Meyer \n" +
                "Wagner \n" +
                "Becker ",
        "In most parts of Germany, beer is simply a drink to be enjoyed. But in Bavaria, it’s a way of life. In Bavaria, the average person drinks around 150 liters of beer per year! Bavarians consume more beer than any other state in Germany. In fact, almost half of the breweries in Germany are in Bavaria. It has the highest density in the whole Federal Republic!",
       "Another fun fact about Germany is that it has over 300 kinds of bread. And that doesn’t even consider regional varieties! There are over 1,200 kinds of pastries, cakes, and other baked goods as well. If you’re a foodie who loves to experience cultural foods, Germany is a one-of-a-kind location for you to try a little bit of everything. ",
       "Hans Riegel of Bonn, Germany created his own sweets company in 1920. He was fed up with his unrewarding career as a confectionary worker. He started off making candies that were hard and colorless and his wife was his delivery person! He came up with the idea to create gelatin-based fruit snacks when his hard candies stopped selling at street fairs. He didn’t completely invent the recipe himself, but he did perfect it. They take inspiration from Turkish delight and Japanese rice candy. ",
       "The first Oktoberfest took place on October 12, 1810. It lasted for five days and was meant to celebrate the marriage between the crown prince of Bavaria (who later became King Ludwig I) to Princess Therese von Sachsen-Hildburghausen. It eventually grew into a yearly anniversary that included an annual agricultural fair, music, and food vendors. As time passed, booths became beer halls and brewers now build temporary structures that hold upwards of 6,000 people. ",
       "There is a popular myth that American president John F. Kennedy stood in front of the Berlin Wall in 1963 and proclaimed to listeners “Ich bin ein Berliner.” Newsrooms across the world told the tale of how the American president proclaimed proudly that he was a “jelly donut.” Berliner is a word for jelly donut but not in Berlin. In Berlin, the word is Pfannkuchen. He indeed delivered a speech on that day. And Kennedy was known for having a hard time with foreign languages. But he knew what he was saying during that speech. ",
       "The Cologne Cathedral is located in Cologne, North Rhine-Westphalia, Germany. It is the largest Gothic church in Northern Europe. It is home to the second-tallest spires. These huge spires make for the largest church in the entire world. When architects started to build the Cologne Cathedral, it was all the way back in 1248. Construction of the cathedral didn’t finish until 1880.",
        "In Germany, it is considered a basic human instinct to do whatever you can to be free. So, if a prisoner attempts to escape from prison, they will not receive additional punishment for the act itself. However, if the prisoner damages any property, hurts anyone, murders anyone, or commits any other crimes while they’re attempting to escape, they will be charged for those crimes. ",
        "Germany is home to some of the most gorgeous castles in the world. There are over 20,000 castles located in Germany, so you’ll have plenty to choose from when you visit. Whether you’re looking to visit famous castles like Neuschwanstein or explore the lesser-known castles, there’s something for everyone. ",

        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntrestingFactsGermanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            generateRandomFact()
            binding.btnImgExit.setOnClickListener {
                initAlertDialogBuilderExit()
            }

            binding.btnNextFact.setOnClickListener {
                generateRandomFact()
            }



        } catch (e: Exception) {
            makeErroe()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun generateRandomFact() {
        binding.tvSingleIntFact.text = listFactsText.random()
        binding.imgIntFacts.setImageResource(listFactsLogo.random())
    }


    private fun makeErroe() {
        Snackbar.make(
            binding.root,
            "Oops. Something went wrong. Please try again.",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initAlertDialogBuilderExit() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("The current data will not be saved. Do you really want to log out?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }
}