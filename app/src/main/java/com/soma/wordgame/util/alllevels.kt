package com.soma.wordgame.util

val allwords = listOf(

    mutableMapOf(
        "NOT" to listOf(5, 10, 15),
        "NUT" to listOf(5, 6, 7),
        "OUT" to listOf(12, 13, 14),
        "TON" to listOf(7, 12, 17),
        "UNTO" to listOf(4,9,14,19)
    ),
    mutableMapOf(
        "BETA" to listOf(2, 3,4,5),
        "BAT" to listOf(2,9,16),
        "BEAT" to listOf(28,29,30,31),
        "BET" to listOf(18, 19, 20),
        "EAT" to listOf(14, 15, 16),
        "TEA" to listOf(20, 27, 34),
        "ATE" to listOf(15, 22, 29),
        "TAB" to listOf(4, 11, 18)
    ),
    mutableMapOf(
        "FOOL" to listOf(6,7,8,9),
        "FLOOD" to listOf(3,9,15,21,27),
        "FOOD" to listOf(24,25,26,27),
        "OLD" to listOf(21,22,23),
        "FOLD" to listOf(5,11,17,23,29),

    ),
    mutableMapOf(
        "CAP" to listOf(0,1,2),
        "ANT" to listOf(1,7,13),
        "TAP" to listOf(13,14,15),
        "ACE" to listOf(14,20,26),

        ),
    mutableMapOf(
        "TRIP" to listOf(5,10,15,20),
        "RIP" to listOf(10,11,12),
        "PRINT" to listOf(20,21,22,23,24),
        "TIP" to listOf(2,7,12),
        "TIN" to listOf(234),

        ),
    mutableMapOf(
        "WALL" to listOf(8,9,10,11),
        "ALL" to listOf(2,6,10),
        "LAW" to listOf(0,4,8)
    ),
    mutableMapOf(
        "SEA" to listOf(12,13,14),
        "EASY" to listOf(2,7,12,17),
        "YES" to listOf(1,2,3),
        "SAY" to listOf(15,16,17)

    ),
    mutableMapOf(
        "PAN" to listOf(15,16,17),
        "PLAN" to listOf(2,7,12,17),
        "LAP" to listOf(7,8,9),
        "PAL" to listOf(9,14,19),
        "NAP" to listOf(0,1,2),

        ),
    mutableMapOf(
        "MANY" to listOf(0,5,10,15),
        "MAN" to listOf(12,13,14),
        "MAY" to listOf(0,1,2),
        "YAM" to listOf(2,7,12),
        "ANY" to listOf(9,14,19),

        ),
    mutableMapOf(
        "AGE" to listOf(15,16,17),
        "GAP" to listOf(0,1,2),
        "PAGE" to listOf(2,7,12,17),
        "APE" to listOf(7,8,9),
        "PEA" to listOf(4,9,14),

        ),
    mutableMapOf(
        "EAR" to listOf(12,13,14),
        "REAR" to listOf(0,1,2,3),
        "ARE" to listOf(2,7,12),
        "RARE" to listOf(0,5,10,15),
        "ERA" to listOf(4,9,14),

        ),
    mutableMapOf(
        "BUS" to listOf(12,18,24),
        "BLUSH" to listOf(1,2,3,4,5),
        "LUSH" to listOf(2,8,14,20),
        "BUSH" to listOf(12,13,14,15),


        ),
    mutableMapOf(
        "PANTRY" to listOf(9,14,19),
        "RAT" to listOf(7,8,9),
        "ART" to listOf(0,1,2),
        "TRAY" to listOf(2,7,12,17),
        "RAY" to listOf(15,16,17),

        ),
    mutableMapOf(
        "THIS" to listOf(0,7,14,21),
        "HIS" to listOf(7,8,9),
        "SIGHT" to listOf(21,22,23,24,25),
        "HITS" to listOf(11,18,25,32),
        "SIT" to listOf(32,33,34),
        "HIT" to listOf(11,12,13),
        "SIGH" to listOf(9,16,23,30),

        ),
    mutableMapOf(
        "TIN" to listOf(4,5,6),
        "TIP" to listOf(4,11,18),
        "PIN" to listOf(7,14,21),
        "RIP" to listOf(16,17,18),
        "PRINT" to listOf(30,31,32,33,34),
        "PIT" to listOf(7,8,9),
        "TRIP" to listOf(9,16,23,30),

        ),
    mutableMapOf(
        "BEND" to listOf(2,3,4,5),
        "BLEND" to listOf(2,9,16,23,30),
        "LEND" to listOf(15,16,17,18),
        "LED" to listOf(28,29,30),
        "DEN" to listOf(18,25,32),
        "END" to listOf(25,26,27),
        "BED" to listOf(13,20,27),

        ),
    mutableMapOf(
        "CAM" to listOf(2,3,4),
        "CLAM" to listOf(2,9,16,23),
        "CLAIM" to listOf(14,15,16,17,18),
        "CALM" to listOf(14,21,28,35),
        "MAIL" to listOf(18,25,32,39),
        "AIM" to listOf(25,26,27),


        ),
    mutableMapOf(
        "HAS" to listOf(2,3,4),
        "ASK" to listOf(3,10,17),
        "SACK" to listOf(14,15,16,17),
        "CASH" to listOf(16,23,30,37),
        "SHACK" to listOf(30,31,32,33,34),
        "ASH" to listOf(35,36,37),

        )



















)

val uniqueCharactersInEachLevel = listOf(
    listOf('N', 'O','T','U'),
    listOf('B', 'E','T','A'),
    listOf('F', 'O', 'O', 'L', 'D'),
    listOf('A', 'P', 'C', 'T', 'E'),
    listOf('P' , 'R', 'I', 'N', 'T'),
    listOf('W', 'A', 'L', 'L'),
    listOf('S', 'Y', 'A', 'E'),
    listOf('A', 'N',  'L', 'P'),
    listOf('Y', 'A', 'N', 'M'),
    listOf('A', 'E', 'G', 'P'),
    listOf('A', 'E', 'R', 'R'),
    listOf('B', 'U', 'S', 'H', 'L'),
    listOf('A', 'Y', 'T', 'R'),
    listOf('T', 'H', 'I', 'S', 'G'),
    listOf('R', 'P', 'I', 'T', 'N'),
    listOf('D', 'B', 'L', 'E', 'N'),
    listOf('C', 'A', 'I', 'M' , 'L'),
    listOf('A', 'K', 'C', 'S' , 'H'),

























    )
val sizes = listOf(
    Pair(4,5),
    Pair(5,7),
    Pair(5,6),
    Pair(5,6),
    Pair(5,5),
    Pair(3,4),
    Pair(4,5),
    Pair(4,5),
    Pair(4,5),
    Pair(4,5),
    Pair(4,5),
    Pair(5,6),
    Pair(4,5),
    Pair(5,7),
    Pair(5,7),
    Pair(5,7),
    Pair(6,7),
    Pair(6,7)

















)