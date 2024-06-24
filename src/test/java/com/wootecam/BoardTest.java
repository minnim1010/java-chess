package com.wootecam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("체스판 테스트")
public class BoardTest {

    @Nested
    class 체스판에_추가된_말의_개수를_조회할_수_있다 {

        static Stream<Arguments> pawnListForSize() {
            return Stream.of(
                    Arguments.arguments(List.of(new Pawn(Color.WHITE))),
                    Arguments.arguments(List.of(new Pawn(Color.WHITE), new Pawn(Color.BLACK))),
                    Arguments.arguments(List.of(new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.WHITE)))
            );
        }

        @MethodSource("pawnListForSize")
        @ParameterizedTest
        public void 말의_개수를_조회할_수_있다(List<Pawn> pawnList) {
            var board = new Board();

            for (Pawn p : pawnList) {
                board.add(p);
            }

            assertThat(board.size()).isEqualTo(pawnList.size());
        }
    }

    @Nested
    class 추가된_순서로_말을_조회할_수_있다 {

        static Stream<Arguments> pawnListForFind() {
            return Stream.of(
                    Arguments.arguments(List.of(new Pawn(Color.WHITE))),
                    Arguments.arguments(List.of(new Pawn(Color.WHITE), new Pawn(Color.BLACK))),
                    Arguments.arguments(List.of(new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.WHITE)))
            );
        }

        @MethodSource("pawnListForFind")
        @ParameterizedTest
        public void 말을_조회할_수_있다(List<Pawn> pawnList) {
            var board = new Board();

            for (Pawn p : pawnList) {
                board.add(p);
            }

            for (int i = 0; i < pawnList.size(); ++i) {
                assertThat(board.findPawn(i)).isEqualTo(pawnList.get(i));
            }
        }

        @ValueSource(ints = {-1, 1})
        @ParameterizedTest
        public void 유효하지_않은_순서로_말을_조회하면_예외가_발생한다(int index) {
            var board = new Board();
            board.add(new Pawn(Color.WHITE));

            assertThatThrownBy(() -> board.findPawn(index))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
