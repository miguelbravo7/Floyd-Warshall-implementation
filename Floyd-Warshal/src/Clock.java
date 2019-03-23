import java.util.concurrent.TimeUnit;

public class Clock {
	long _start;
	long _finish;

	public Clock() {
		_start = System.currentTimeMillis();
	}

	public String toString() {
		long tiempo = elapsedTime();
		return String.format("%d horas, %d min, %d sec, %d ms", TimeUnit.MILLISECONDS.toHours(tiempo) % 24,
				TimeUnit.MILLISECONDS.toMinutes(tiempo) % 60, TimeUnit.MILLISECONDS.toSeconds(tiempo) % 60,
				tiempo % 1000);
	}

	public static Clock start() {
		return new Clock();
	}

	public void stop() {
		_finish = System.currentTimeMillis();
	}

	public long elapsedTime() {
		return _finish - _start;
	}
}