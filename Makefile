clean:
	./gradlew clean

test_unit:
	./gradlew testDebugUnitTest

test_end_to_end:
	sh maestro/end_to_end.sh

install_maestro:
	sh maestro/install_maestro.sh