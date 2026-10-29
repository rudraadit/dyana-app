package com.example.data.content

data class AsanaStep(
    val stepNumber: Int,
    val name: String,
    val sanskritName: String,
    val mantra: String = "",
    val breathingCue: String,
    val durationSeconds: Int,
    val instruction: String,
    val benefit: String
)

data class YogaFlow(
    val id: String,
    val title: String,
    val subtitle: String,
    val durationMinutes: Int,
    val badgeColorHex: Long,
    val tag: String,
    val imageUrl: String,
    val description: String,
    val steps: List<AsanaStep>
)

object YogaContent {
    val suryaNamaskar12Steps = listOf(
        AsanaStep(
            1, "Prayer Pose", "Pranamasana", "Om Mitraya Namaha",
            "Exhale", 15,
            "Stand at the front edge of the mat, feet together, palms together in front of the heart center.",
            "Calms nervous system, improves poise and centered posture."
        ),
        AsanaStep(
            2, "Raised Arms Pose", "Hastauttanasana", "Om Ravaye Namaha",
            "Inhale", 15,
            "Lift arms upward and gently arch backward, stretching abdominal muscles and spine.",
            "Stretches abdomen, expands chest and lungs, tones shoulder muscles."
        ),
        AsanaStep(
            3, "Standing Forward Bend", "Hastapadasana", "Om Suryaya Namaha",
            "Exhale", 15,
            "Bend forward from hips keeping spine straight, bring hands down to touch the ground beside feet.",
            "Enhances blood flow to the brain, invigorates liver and kidneys."
        ),
        AsanaStep(
            4, "Equestrian Pose", "Ashwa Sanchalanasana", "Om Bhanave Namaha",
            "Inhale", 20,
            "Step right leg far back, drop right knee to mat, push pelvis forward and look gently upward.",
            "Strengthens thigh muscles, tones liver, promotes digestive fire."
        ),
        AsanaStep(
            5, "Stick / Plank Pose", "Dandasana", "Om Khagaya Namaha",
            "Retain", 15,
            "Step left leg back parallel to right leg, keeping entire body in a straight diagonal plank.",
            "Builds core strength, arms, wrists and concentration power."
        ),
        AsanaStep(
            6, "Salute with 8 Points", "Ashtanga Namaskara", "Om Pushne Namaha",
            "Exhale", 15,
            "Lower knees, chest and chin to touch the mat; hips remain elevated off the floor.",
            "Develops chest and arm muscles, aligns upper and middle back."
        ),
        AsanaStep(
            7, "Cobra Pose", "Bhujangasana", "Om Hiranyagarbhaya Namaha",
            "Inhale", 20,
            "Glide chest forward and arch upward, shoulders rolled back, elbows gently bent.",
            "Stimulates adrenal glands, relieves spinal tension, expands lung capacity."
        ),
        AsanaStep(
            8, "Downward-Facing Dog", "Adho Mukha Svanasana", "Om Marichaye Namaha",
            "Exhale", 20,
            "Lift hips high forming an inverted V, press heels down and push shoulder blades wide.",
            "Relieves fatigue, calms brain, boosts circulation to cranial nerves."
        ),
        AsanaStep(
            9, "Equestrian Pose", "Ashwa Sanchalanasana", "Om Adityaya Namaha",
            "Inhale", 20,
            "Step right foot forward between palms, left knee grounded, chin tilted softly upwards.",
            "Balances hip flexibility and activates inner vitality."
        ),
        AsanaStep(
            10, "Hand to Feet Pose", "Hastapadasana", "Om Savitre Namaha",
            "Exhale", 15,
            "Step left foot forward beside right foot, head drawn towards shins.",
            "Massages abdominal organs, stretches hamstrings and calf muscles."
        ),
        AsanaStep(
            11, "Raised Arms Pose", "Hastauttanasana", "Om Arkaya Namaha",
            "Inhale", 15,
            "Inhale while sweeping arms upward, extending spine back with gentle gaze.",
            "Opens thoracic cavity, restores energetic vigor."
        ),
        AsanaStep(
            12, "Mountain / Prayer Pose", "Tadasana", "Om Bhaskaraya Namaha",
            "Exhale", 20,
            "Lower palms into Anjali Mudra at the heart center, close eyes and observe heartbeat.",
            "Integrates prana, returns body to perfect balanced equilibrium."
        )
    )

    val flows = listOf(
        YogaFlow(
            id = "mind_fresh",
            title = "Mind Fresh Yoga",
            subtitle = "Mental clarity & alertness",
            durationMinutes = 15,
            badgeColorHex = 0xFF34D399,
            tag = "15 Min",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDLpch-Nc-8VI1r-ZAprRl7RmH5YGRrJ0JWMO5G1Z1HTQA0Sp3xrraBi6RJAV5jaKVOzplaok6rWEC_cq_ALnKwd6stj2urClFsO1TVxs6_xUaC5ToK19P5sU4uFIuH8WqW5p4ymebIQkjG1_cCE0oMeLtmi2S5yY57BzV-OFz2TF1LDSULvk5_FDmpAaOVaHGmh8hgietx7npBCz-cQRjPGob2GFT1NhhF_esmGP0_",
            description = "Gentle flow designed specifically for students to dissolve study fatigue, relieve neck and eye tension, and heighten alertness.",
            steps = listOf(
                AsanaStep(1, "Easy Grounding Pose", "Sukhasana", "", "Steady Inhale/Exhale", 180, "Sit cross-legged with hands in Gyan Mudra, observing cool air entering nostrils.", "Relaxes mental chatter and calms sympathetic stress."),
                AsanaStep(2, "Cat-Cow Dynamic Spine", "Marjaryasana-Bitilasana", "", "Inhale Arch / Exhale Round", 180, "Move between arched spine looking up and rounded back looking at navel.", "Mobilizes cerebrospinal fluid and dispels lethargy."),
                AsanaStep(3, "Downward Dog Inversion", "Adho Mukha Svanasana", "", "Deep Slow Exhale", 180, "Form inverted V, grounding heels and elongating hamstrings.", "Brings freshly oxygenated blood to cranial centers."),
                AsanaStep(4, "Standing Forward Fold", "Uttanasana", "", "Soft rhythmic breath", 180, "Fold gently from pelvic crease with knees relaxed, head hanging free.", "Releases study desk posture tension in back and shoulders."),
                AsanaStep(5, "Corpse Pose Integration", "Shavasana", "", "Natural breath surrender", 180, "Lie flat on back with palms open to the sky, releasing all effort.", "Absorbs neural benefits and resets parasympathetic calmness.")
            )
        ),
        YogaFlow(
            id = "memorise_yoga",
            title = "Memorise Yoga",
            subtitle = "Memory retention & dharana",
            durationMinutes = 20,
            badgeColorHex = 0xFFFBBF24,
            tag = "20 Min",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuALs1tLEG7ITshB-_zaNSgBbApTNu-cyMqvDOKeKRse34OIIgI1uitN_eKLnnh4KkaRbnlvc1xLrjUxiFLal0tWJjpLjwO8Qho5f7t1Rbq6MJHLliAiAfPLwjNrggdAtMGIqneA2zoEXpori7ariP4DmEAw0yf0N5sP-l1d702iEaV5vIbrSVdJVKvsBbaG3I9g9d33U7wmLMTUshJOhoOccu5jq5IStcCCq3w95tse",
            description = "Targeted cognitive asanas focusing on Dharana (single-pointed concentration), balancing left and right cerebral hemispheres.",
            steps = listOf(
                AsanaStep(1, "Lotus Pose Concentration", "Padmasana", "", "Ujjayi Breath", 240, "Lock feet into lotus, spine upright like a pillar of light, focus on third eye.", "Activates Sushumna nadi and strengthens single-pointed focus."),
                AsanaStep(2, "Tree Pose Equilibrium", "Vrikshasana", "", "Smooth Inhale & Exhale", 240, "Balance on left leg, right sole on inner thigh, hands raised in prayer.", "Trains cerebellum for unbroken cognitive equilibrium."),
                AsanaStep(3, "Shoulder Stand Inversion", "Sarvangasana", "", "Deep Chest Breathing", 240, "Lift legs and torso supported by hands on lower back, chin locked at chest.", "Stimulates thyroid and floods cerebral cortex with nutrient-rich blood."),
                AsanaStep(4, "Seated Forward Bend", "Paschimottanasana", "", "Long Slow Exhale", 240, "Reach hands forward toward toes with long spine, relaxing forehead to knees.", "Directly stimulates memory pathways in hippocampus."),
                AsanaStep(5, "Humming Bee Resonance", "Bhramari Pranayama", "", "Inhale nose, Exhale hum 'Mmm'", 240, "Close ears with thumbs, generate soothing high humming sound inside skull.", "Creates nitric oxide surge and induces deep theta-brainwave calm.")
            )
        ),
        YogaFlow(
            id = "brahmacharya",
            title = "Brahmacharya Yoga",
            subtitle = "Ojas preservation & poise",
            durationMinutes = 25,
            badgeColorHex = 0xFFFB923C,
            tag = "25 Min",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBesZuSvu02SK8Xf4G4vdV4tGzTCwqi5s1G3gBwL0jJn5-hyH_HA3jX4IE9ViETBAHXkbNqbJj0eRf0lJvgZ-9Ym5cKYEb9ckKj4aUAMHtSs2OnyK9FNWOZ539A03_ijs1YGFCMmRNJ8nzhOJPzyLIuy8WHQM4OPQCJFzrr8fUtkV5wrihIjwuaWKoASNZoQqBbWil6LUhqcmyX7EBbJkRYdkOPuYNZ3QAlu7dmH4bj",
            description = "Preserves vital force (Ojas), directs instinctive energy upward into intellectual and spiritual luster (Medha & Tejas).",
            steps = listOf(
                AsanaStep(1, "Adept's Master Pose", "Siddhasana", "", "Slow abdominal breath", 300, "Sit with heel pressed against perineum, toes tucked between thigh and calf.", "Regulates lower energy centers and cools restless sensory desires."),
                AsanaStep(2, "Root Lock & Pelvic Seal", "Ashwini Mudra & Mula Bandha", "", "Inhale-Lock, Exhale-Release", 300, "Gently contract and lift pelvic floor muscles synchronously with breath.", "Sublimates reproductive energy into mental radiance (Ojas Shakti)."),
                AsanaStep(3, "Fish Pose Opening", "Matsyasana", "", "Deep thoracic expansion", 300, "Arch chest with crown of head on floor, elbows supporting torso weight.", "Relieves chest constriction and activates thymus immunity."),
                AsanaStep(4, "Plow Pose Purification", "Halasana", "", "Controlled gentle breath", 300, "Swing legs overhead to rest toes on mat behind crown.", "Stimulates entire sympathetic chain and calms instinctual agitation."),
                AsanaStep(5, "Sattvic Inner Absorption", "Yoga Nidra", "", "Total surrender", 300, "Rotate awareness through bodily points with resolution of pure discipline.", "Integrates purity of mind and emotional stability.")
            )
        ),
        YogaFlow(
            id = "leadership",
            title = "Leadership Yoga",
            subtitle = "Confidence & stamina",
            durationMinutes = 18,
            badgeColorHex = 0xFFC7D2FE,
            tag = "18 Min",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDN8txL7xHz4MnfWiyGyfv0YF0IUjpqQi9oC2b4PJGtLQ4Zb3vLtzwT_Rh389WWzj7I3ADricXkRafddiPRxQ5UyNBYTMTSSYsdG6VDi7lttCazPR5Q92sR0cOHoh_WtlnxhMYuaQGpf1MjH6U8z1k-hclq5TEYokWZJ9NVMEtpoYckUEaTMuFvF1MTeJ_LtXPSssfCZFrEtEq6wXQVIdBPAD-PcEHChHRA8U8AwRI3",
            description = "Instills moral courage, unwavering physical endurance, and the charismatic poise of a Vedic student leader.",
            steps = listOf(
                AsanaStep(1, "Warrior Pose of Courage", "Virabhadrasana I", "", "Bold deep breath", 240, "Deep forward lunge, arms raised high, gaze forward with noble stillness.", "Develops leg stamina, determination, and opens heart space."),
                AsanaStep(2, "Warrior II Focused Gaze", "Virabhadrasana II", "", "Steady lung breath", 240, "Arms extended horizontally, gazing past front fingertips with laser clarity.", "Teaches steadfastness and decisive action under pressure."),
                AsanaStep(3, "Chair Pose of Strength", "Utkatasana", "", "Deep rhythmic breathing", 240, "Bend knees as if sitting on an invisible throne, arms stretching upward.", "Strengthens thigh willpower and resilience against adversity."),
                AsanaStep(4, "Lion's Fearless Breath", "Simhasana", "", "Forceful Exhale with 'Haa'", 240, "Kneel, place hands on knees, open mouth wide, extend tongue, roar breath.", "Clears throat chakra, overcomes public speaking fear and hesitation."),
                AsanaStep(5, "Standing Pillar of Light", "Samasthiti", "", "Calm noble posture", 120, "Stand tall, shoulder blades broad, grounding feet into mother earth.", "Radiates inner composure and moral authority.")
            )
        ),
        YogaFlow(
            id = "huta_yoga",
            title = "Huta Yoga",
            subtitle = "Vital force balance",
            durationMinutes = 30,
            badgeColorHex = 0xFFFBBF24,
            tag = "30 Min",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDTr_55p6lZ44Qc9ZqoqtCz0aw_cp9fmt86aeIktOKeHEvbVH9Aiwx_6xwe1LjzZe-MZ2c1pD_bZ7qaBJpF8FmnsVMIDpDK0t8GAJ5hDqxOUAIs5MNJSWt2vxg5VBnD-yx-RG9QDlRFiEMVEwJ3oabzuGqq12NhXwiVGWyl79XYKlh1Vga94zliOOmlqF3C34eKRoiolIefDJP8wHIXfY3Hu-YmcfnjT5SV440Qngly",
            description = "Sacred hatha practice uniting solar (Ha) and lunar (Tha) energies to purify physical nadis and awaken inner fire.",
            steps = listOf(
                AsanaStep(1, "Fire Cleansing Breath", "Kapalbhati", "", "Active Exhale, Passive Inhale", 360, "Rhythmic rapid abdominal pumping to burn internal impurities.", "Awakens solar plexus (Manipura) and dispels mental sluggishness."),
                AsanaStep(2, "Bow Pose Elevation", "Dhanurasana", "", "Inhale Lift", 360, "Lie prone, hold ankles and pull backward to arch body like a strung bow.", "Energizes spinal cord, stimulates digestion, builds backbone fortitude."),
                AsanaStep(3, "Cobra Fire Pose", "Bhujangasana", "", "Smooth Inhale", 360, "Slow deliberate arch of upper torso without pushing hands into ground.", "Cultivates internal heat and expands heart vitality."),
                AsanaStep(4, "Spinal Twist Integration", "Ardha Matsyendrasana", "", "Exhale Twist", 360, "Seated spinal rotation, looking gently over back shoulder.", "Flushes toxins from organs and balances autonomic nervous system."),
                AsanaStep(5, "Cosmic Dissolution Rest", "Maha Shavasana", "", "Breath awareness", 360, "Complete conscious relaxation from toe to crown.", "Converts physical exertion into refined spiritual luminescence.")
            )
        )
    )
}
