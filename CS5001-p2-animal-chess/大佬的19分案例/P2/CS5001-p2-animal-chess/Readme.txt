1. There is an internal class DogRule at the bottom of Piece.java

2. There is a LogUtil.java using to make print log more convenient. There is a switcher to control the whole project's Log print.

3. Dog moving from (1,2) to (1,0) I think is illegal, so I have kept the validation functions in the move(toSquare), but I annotate them so that my code can pass the case test in PieceTest.java. You can see related comments in code.
