package com.example.data.content

data class ScriptureChapter(
    val chapterNumber: Int,
    val title: String,
    val summary: String,
    val verses: List<ScriptureVerse>
)

data class ScriptureVerse(
    val verseNumber: String,
    val sanskritText: String,
    val transliteration: String,
    val englishMeaning: String,
    val studentApplication: String
)

data class SpiritualBook(
    val id: String,
    val title: String,
    val subtitle: String,
    val categoryBadge: String,
    val badgeColorHex: Long,
    val coverImageUrl: String,
    val author: String,
    val description: String,
    val chapters: List<ScriptureChapter>
)

object ScriptureContent {
    val books = listOf(
        SpiritualBook(
            id = "gita",
            title = "Bhagavad Gita",
            subtitle = "Youth Edition",
            categoryBadge = "Ch. 2",
            badgeColorHex = 0xFFFBBF24,
            coverImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBuFbaTCaA7tUIMcq5WDOMkqQe9c8hYeVUOuG41YhlAkSusSjaZkKR4PzIYixyOf0wsBxbvMFVegU9ggrVNdcHvN5n1-Sv0kJWI7OIrRXb7L3Wca5lvA6lWUP4nlzvtL4RrQbEkEHfrN6XDyeRkKEpmXMBvaTdR_Ju9Qmw8QLNeAUp_ldUlb7pkh7dx40TKDnxLksRb6TD0fRlN9GqWWJcaHn-tJ6Uy2jcynAGD12LT",
            author = "Bhagavan Sri Krishna",
            description = "The timeless dialogue on duty, conquering self-doubt, mastering the intellect, and remaining steadfast amid academic and life challenges.",
            chapters = listOf(
                ScriptureChapter(
                    chapterNumber = 2,
                    title = "Sankhya Yoga (The Yoga of Knowledge)",
                    summary = "Sri Krishna instructs Arjuna on the eternal nature of the soul, the discipline of Nishkama Karma (selfless action), and the characteristics of a person with steady wisdom (Sthitaprajna).",
                    verses = listOf(
                        ScriptureVerse(
                            verseNumber = "2.47",
                            sanskritText = "कर्मण्येवाधिकारस्ते मा फलेषु कदाचन।\nमा कर्मफलहेतुर्भूर्मा ते सङ्गोऽस्त्वकर्मणि॥",
                            transliteration = "Karmany-evādhikāras te mā phaleṣhu kadāchana\nmā karma-phala-hetur bhūr mā te saṅgo ’stvakarmaṇi",
                            englishMeaning = "You have a right only to perform your prescribed duty, but never to its fruits. Let not the fruit of action be your motive, nor let your attachment be to inaction.",
                            studentApplication = "Focus 100% on deep study and diligent preparation without paralyzing exam anxiety about the final marks. Excellence follows absorbed effort."
                        ),
                        ScriptureVerse(
                            verseNumber = "2.48",
                            sanskritText = "योगस्थ: कुरु कर्माणि सङ्गं त्यक्त्वा धनञ्जय।\nसिद्ध्यसिद्ध्यो: समो भूत्वा समत्वं योग उच्यते॥",
                            transliteration = "Yoga-sthaḥ kuru karmāṇi saṅgaṁ tyaktvā dhanañjaya\nsiddhy-asiddhyoḥ samo bhūtvā samatvaṁ yoga uchyate",
                            englishMeaning = "Perform your work being steadfast in yoga, renouncing selfish attachment. Be balanced in both success and failure; equanimity of mind is defined as Yoga.",
                            studentApplication = "Maintain emotional equilibrium when receiving feedback or test scores. Neither become arrogant with praise nor despondent with critique."
                        ),
                        ScriptureVerse(
                            verseNumber = "2.62",
                            sanskritText = "ध्यायतो विषयान्पुंस: सङ्गस्तेषूपजायते।\nसङ्गात्सञ्जायते काम: कामात्क्रोधोऽभिजायते॥",
                            transliteration = "Dhyāyato viṣhayān puṁsaḥ saṅgas teṣhūpajāyate\nsaṅgāt sañjāyate kāmaḥ kāmāt krodho ’bhijāyate",
                            englishMeaning = "Brooding on sensory attractions creates attachment; attachment breeds obsessive craving, and thwarted craving gives rise to anger and agitation.",
                            studentApplication = "Notice the moment your thoughts obsess over digital distractions or social media. Nip curiosity at the root before it turns into compulsive addiction."
                        ),
                        ScriptureVerse(
                            verseNumber = "2.70",
                            sanskritText = "आपूर्यमाणमचलप्रतिष्ठं समुद्रमाप: प्रविशन्ति यद्वत्।\nतद्वत्कामा यं प्रविशन्ति सर्वे स शान्तिमाप्नोति न कामकामी॥",
                            transliteration = "Āpūryamāṇam achala-pratiṣhṭhaṁ samudram āpaḥ praviśhanti yadvat\ntadvat kāmā yaṁ praviśhanti sarve sa śhāntim āpnoti na kāma-kāmī",
                            englishMeaning = "Just as rivers enter the ocean, which is filled from all sides yet remains undisturbed, so does the sage attain peace into whom all desires enter without destabilizing.",
                            studentApplication = "Like the ocean, let exams, peer opinions, and daily fluctuations flow into your calm mind without disturbing your inner baseline peace."
                        )
                    )
                )
            )
        ),
        SpiritualBook(
            id = "mind_mastery",
            title = "Mind Mastery",
            subtitle = "Thought Control",
            categoryBadge = "Vol. 1",
            badgeColorHex = 0xFF34D399,
            coverImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBuVogMT4iNHS8AkNBJ7ZgcecGmYRgAun1L5nOZXjthU2tWshBezAHu1LdWUBuWrH2zOLmBIcn4EAI1qlh5LhZwuMUqBA9baaO8TvE1ZN0es1gkch6MtxO2CC-9a7TuI3kgWJAnMoL7BYn9d1OKsowNVUSsfSQUA5fD0MJycrXqlOOkcp7J1bgTa3fiAMSZTkwAuF4jtb2rpdIOWfPv-loMiJobi-MEt6U8tLjgcGwL",
            author = "Swami Vivekananda & Vedic Seers",
            description = "A practical manual for training the restless student mind, cultivating razor-sharp attention span, and eliminating mental fatigue.",
            chapters = listOf(
                ScriptureChapter(
                    chapterNumber = 1,
                    title = "The Anatomy of Restless Thought",
                    summary = "Understanding Chitta Vritti (mental ripples) and the mechanism of sensory inputs. Why the untrained mind constantly jumps from past regret to future worry.",
                    verses = listOf(
                        ScriptureVerse(
                            verseNumber = "MM 1.1",
                            sanskritText = "योगश्चित्तवृत्तिनिरोधः",
                            transliteration = "Yogaś chitta-vṛtti-nirodhaḥ",
                            englishMeaning = "Yoga is the intentional restraint and mastering of the modifications of the mind-stuff.",
                            studentApplication = "Study is Raja Yoga in action. True learning occurs when random mental chatter slows down, allowing direct intellectual absorption."
                        ),
                        ScriptureVerse(
                            verseNumber = "MM 1.2",
                            sanskritText = "तदा द्रष्टुः स्वरूपेऽवस्थानम्",
                            transliteration = "Tadā draṣṭuḥ svarūpe ’vasthānam",
                            englishMeaning = "Then the Observer abides in its own true radiant nature.",
                            studentApplication = "You are not your anxious thoughts or fleeting doubts. You are the calm conscious witness behind them."
                        )
                    )
                ),
                ScriptureChapter(
                    chapterNumber = 2,
                    title = "The 5-Step Thought Filter Technique",
                    summary = "How to pause before reacting to stress, inspect thoughts using the Viveka (discernment) razor, and redirect attention to present action.",
                    verses = listOf(
                        ScriptureVerse(
                            verseNumber = "MM 2.1",
                            sanskritText = "अभ्यासवैराग्याभ्यां तन्निरोधः",
                            transliteration = "Abhyāsa-vairāgyābhyāṁ tan-nirodhaḥ",
                            englishMeaning = "Control of mental whirlpools is accomplished through persistent practice (Abhyasa) and calm dispassion (Vairagya).",
                            studentApplication = "When your mind drifts off a textbook page 20 times, bring it back 20 times with a smile. Every return strengthens cognitive neural circuits."
                        )
                    )
                )
            )
        ),
        SpiritualBook(
            id = "self_control",
            title = "Self Control",
            subtitle = "Brahmacharya Path",
            categoryBadge = "Daily",
            badgeColorHex = 0xFFFB923C,
            coverImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBZNVBw0sRaa5TNBam6ViwxS4fn09XRkGyhbhUB5HxAXRX-0XDyXnOppCTh0hVzwlnzzRZJ7tpLfEM5zkiBNctdr629HVihQeHJujclyCtciRKRrOdqa8LEPXrUAOZZseRUczBZcI2RQ9lVQTnNhaQapn40o4fk3U7dr0zOrm-sokRmRQrhZSIkZsKUWvZPg5G0_SyAidqxsp-pbCucgho9kwYKBSoUQwBI0ENdN4Gq",
            author = "Mahatma Gandhi & Patanjali",
            description = "Preserving the sacred vital nectar (Ojas) through clean habits, truthful speech, moderated sensory intake, and disciplined daily student conduct.",
            chapters = listOf(
                ScriptureChapter(
                    chapterNumber = 1,
                    title = "The Principle of Energy Sublimation",
                    summary = "Physical vitality, when preserved through wholesome thoughts and sattvic diet, automatically transforms into intellectual brilliance (Medha Shakti) and photographic memory.",
                    verses = listOf(
                        ScriptureVerse(
                            verseNumber = "SC 1.1",
                            sanskritText = "ब्रह्मचर्यप्रतिष्ठायां वीर्यलाभः",
                            transliteration = "Brahmacharya-pratiṣhṭhāyāṁ vīrya-lābhaḥ",
                            englishMeaning = "Upon steadfast establishment in pure discipline and continence, boundless vigor and intellectual courage are attained.",
                            studentApplication = "Protecting physical and mental purity gives an extraordinary memory, sparkling eyes, and unwavering courage in youth."
                        ),
                        ScriptureVerse(
                            verseNumber = "SC 1.2",
                            sanskritText = "आहारशुद्धौ सत्त्वशुद्धिः सत्त्वशुद्धौ ध्रुवा स्मृतिः",
                            transliteration = "Āhāra-śhuddhau sattva-śhuddhiḥ sattva-śhuddhau dhruvā smṛtiḥ",
                            englishMeaning = "When food and sensory inputs are pure, inner nature becomes luminous; when inner nature is luminous, memory remains unwavering.",
                            studentApplication = "Avoid junk food, late-night binge watching, and toxic arguments. Clean sensory fuel powers sharp academic insight."
                        )
                    )
                )
            )
        ),
        SpiritualBook(
            id = "ancient_wisdom",
            title = "Ancient Wisdom",
            subtitle = "Vedic Principles",
            categoryBadge = "Upanishads",
            badgeColorHex = 0xFFC7D2FE,
            coverImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCK0ju85D2WfkywswEXB5hVVBJUe95-82Hobmr93haMbiO6Ag-2ewFXxjg5bgLjaMs-ZlV4iiDpwicbQTuZAZjhwR5AvzEoTuUaVZ2UOJqlB2fPn11jC8J3_Py5Q_dmZ3NCZ0O0FD3o8xOhIAGtQOjRjqE7jxjkc3NBlzYCiwki5bXojEFN5KnZV1VluS2n72xJtnxwOVhZSX0a_Cd4TNZEXXevNxUAw8isBZM7_d7a",
            author = "Upanishadic Risis",
            description = "Exploring the cosmic unity of individual consciousness (Atman) and universal reality (Brahman), and the scientific rationale behind Indian spiritual customs.",
            chapters = listOf(
                ScriptureChapter(
                    chapterNumber = 1,
                    title = "Scientific Rationale of Vedic Practices",
                    summary = "Why wake up in Brahma Muhurta? Why chant AUM at 432Hz? The biological and electromagnetic significance of daily rituals.",
                    verses = listOf(
                        ScriptureVerse(
                            verseNumber = "VR 1.1",
                            sanskritText = "ब्राह्मे मुहूर्ते उत्तिष्ठेत् स्वस्थो रक्षार्थम् आयुषः",
                            transliteration = "Brāhme muhūrte uttiṣhṭhet svastho rakṣhārtham āyuṣhaḥ",
                            englishMeaning = "One desiring health and long vibrant life should awaken during the auspicious Brahma Muhurta (approx. 96 minutes before sunrise).",
                            studentApplication = "Atmospheric nascent oxygen and ozone levels peak before dawn. Cortisol remains low while melatonin harmonizes, making study retention 3x faster."
                        ),
                        ScriptureVerse(
                            verseNumber = "VR 1.2",
                            sanskritText = "ओमित्येकाक्षरं ब्रह्म",
                            transliteration = "Om ity ekākṣharaṁ brahma",
                            englishMeaning = "The primordial syllable OM is the condensed ultrasonic vibration of the cosmos.",
                            studentApplication = "Vocalizing A-U-M produces tactile resonance in chest, throat, and skull, stimulating the vagus nerve and reducing heart-rate variability."
                        )
                    )
                )
            )
        )
    )
}
