import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Move implements Rule {
	private int side = 0;

	@Override
	public void move(Cell[][] cells, int row, int colum, Draw d) {
		outloop: for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].isChoose()) {
					if ((row != i || colum != j) && cells[row][colum].getFlag() != 0) {
						// 兵
						if (cells[i][j].getFlag() == -6 && cells[i][j].getFlag() * cells[row][colum].getFlag() < 0) {
							if (Math.abs(i - row) == 1 && Math.abs(j - colum) == 1) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
						}
						// 車
						if (cells[i][j].getFlag() == -5 && cells[i][j].getFlag() * cells[row][colum].getFlag() < 0) {
							if (row == i) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							} else if (colum == j) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
						}
						// 马
						if (cells[i][j].getFlag() == -4 && cells[i][j].getFlag() * cells[row][colum].getFlag() < 0) {
							if (Math.abs(row - i) == 1 && Math.abs(colum - j) == 2) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							} else if (Math.abs(row - i) == 2 && Math.abs(colum - j) == 1) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
						}
						// 象
						if (cells[i][j].getFlag() == -3 && cells[i][j].getFlag() * cells[row][colum].getFlag() < 0) {
							if (Math.abs(row - i) == Math.abs(colum - j)) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
						}
						// 后
						if (cells[i][j].getFlag() == -2 && cells[i][j].getFlag() * cells[row][colum].getFlag() < 0) {
							if (row == i) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							} else if (colum == j) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
							if (Math.abs(row - i) == Math.abs(colum - j)) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
						}
						// 王
						if (cells[i][j].getFlag() == -1 && cells[i][j].getFlag() * cells[row][colum].getFlag() < 0) {
							if (i == row && Math.abs(j - colum) == 1) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							} else if (j == colum && Math.abs(i - row) == 1) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
							if (Math.abs(i - row) == 1 && Math.abs(j - colum) == 1) {
								cells[row][colum].setFlag(cells[i][j].getFlag());
								cells[i][j].setFlag(0);
								side = 1;
							}
						}
						cells[i][j].setChoose(false);
						cells[row][colum].setChoose(true);
						d.highLight();
						if (side == 1) {
							this.getCanWalk(cells);
							this.pcMove(cells, d);
							d.initPane();
							d.highLight();
							for (int p = 0; p < 8; ++p) {
								for (int q = 0; q < 8; ++q) {
									cells[p][q].getCanWalk().clear();
								}
							}
							side = 0;
						}
						break outloop;
					} else if (cells[row][colum].getFlag() == 0) {
						if (Math.abs(cells[i][j].getFlag()) == 6)
							soldier(cells, row, colum);
						if (Math.abs(cells[i][j].getFlag()) == 5)
							car(cells, row, colum);
						if (Math.abs(cells[i][j].getFlag()) == 4)
							horse(cells, row, colum);
						if (Math.abs(cells[i][j].getFlag()) == 3)
							elephant(cells, row, colum);
						if (Math.abs(cells[i][j].getFlag()) == 2)
							queen(cells, row, colum);
						if (Math.abs(cells[i][j].getFlag()) == 1)
							king(cells, row, colum);
						d.initPane();
						d.highLight();
						if (side == 1) {
							this.getCanWalk(cells);
							this.pcMove(cells, d);
							d.initPane();
							d.highLight();
							for (int p = 0; p < 8; ++p) {
								for (int q = 0; q < 8; ++q) {
									cells[p][q].getCanWalk().clear();
								}
							}
							side = 0;
						}
						break outloop;
					}
				}
				if (i == 7 && j == 7) {
					cells[row][colum].setChoose(true);
					d.highLight();
				}
			}
		}
	}

	public void soldier(Cell[][] cells, int row, int colum) {
		outloop: for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].isChoose()) {
					Cell cell = cells[i][j];
					if (cell.getRow() == 1) {
						if (row - cell.getRow() <= 2 && colum == cell.getColum()) {
							cells[row][colum].setFlag(cell.getFlag());
							cells[row][colum].setChoose(true);
							cell.setFlag(0);
							cell.setChoose(false);
							cells[row][colum].setChoose(true);
							side = 1;
							break outloop;
						}
					} else if (cell.getRow() == 6) {
						if (cell.getRow() - row <= 2 && colum == cell.getColum()) {
							cells[row][colum].setFlag(cell.getFlag());
							cells[row][colum].setChoose(true);
							cell.setFlag(0);
							cell.setChoose(false);
							side = 1;
							break outloop;
						}
					}
					if (cell.getFlag() == 6) {
						if (row - cell.getRow() == 1 && colum == cell.getColum()) {
							cells[row][colum].setFlag(cell.getFlag());
							cells[row][colum].setChoose(true);
							cell.setFlag(0);
							cell.setChoose(false);
							cells[row][colum].setChoose(true);
							side = 1;
							break outloop;
						}
					} else if (cell.getFlag() == -6) {
						if (cell.getRow() - row == 1 && colum == cell.getColum()) {
							cells[row][colum].setFlag(cell.getFlag());
							cells[row][colum].setChoose(true);
							cell.setFlag(0);
							cell.setChoose(false);
							side = 1;
							break outloop;
						}
					}
				}
			}
		}
	}

	public void car(Cell[][] cells, int row, int colum) {
		outloop: for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].isChoose()) {
					if (i == row) {
						if (j <= colum) {
							for (int m = j + 1; m < j + Math.abs(j - colum) + j + 1; ++m) {
								if (cells[i][m].getFlag() != 0) {
									break;
								}
								if (m == colum) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else {
							for (int m = j - 1; m >= j - Math.abs(j - colum); --m) {
								if (cells[i][m].getFlag() != 0) {
									break;
								}
								if (m == colum) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						}

					} else if (j == colum) {
						if (i >= row) {
							for (int m = i - 1; m >= i - Math.abs(i - row); --m) {
								if (cells[m][j].getFlag() != 0) {
									break;
								}
								if (m == row) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else {
							for (int m = i + 1; m < i + Math.abs(i - row) + 1 + i; ++m) {
								if (cells[m][j].getFlag() != 0) {
									break;
								}
								if (m == row) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						}
					}
					break outloop;
				}
			}
		}
	}

	public void horse(Cell[][] cells, int row, int colum) {
		outloop: for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].isChoose()) {
					if (Math.abs(row - i) == 1 && Math.abs(colum - j) == 2) {
						cells[row][colum].setFlag(cells[i][j].getFlag());
						cells[row][colum].setChoose(true);
						cells[i][j].setFlag(0);
						cells[i][j].setChoose(false);
						side = 1;
						break outloop;
					} else if (Math.abs(row - i) == 2 && Math.abs(colum - j) == 1) {
						cells[row][colum].setFlag(cells[i][j].getFlag());
						cells[row][colum].setChoose(true);
						cells[i][j].setFlag(0);
						cells[i][j].setChoose(false);
						side = 1;
						break outloop;
					}
				}
			}
		}
	}

	public void elephant(Cell[][] cells, int row, int colum) {
		outloop: for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].isChoose()) {
					if (Math.abs(i - row) == Math.abs(j - colum)) {
						if (i > row && j > colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i - m][j - m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else if (i > row && j < colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i - m][j + m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else if (i < row && j < colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i + m][j + m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else if (i < row && j > colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i + m][j - m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						}
						break outloop;
					}
				}
			}
		}
	}

	public void queen(Cell[][] cells, int row, int colum) {
		outloop: for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].isChoose()) {
					if (i == row) {
						if (j <= colum) {
							for (int m = j + 1; m < j + Math.abs(j - colum) + j + 1; ++m) {
								if (cells[i][m].getFlag() != 0) {
									break;
								}
								if (m == colum) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else {
							for (int m = j - 1; m >= j - Math.abs(j - colum); --m) {
								if (cells[i][m].getFlag() != 0) {
									break;
								}
								if (m == colum) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						}

					} else if (j == colum) {
						if (i >= row) {
							for (int m = i - 1; m >= i - Math.abs(i - row); --m) {
								if (cells[m][j].getFlag() != 0) {
									break;
								}
								if (m == row) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else {
							for (int m = i + 1; m < i + Math.abs(i - row) + 1 + i; ++m) {
								if (cells[m][j].getFlag() != 0) {
									break;
								}
								if (m == row) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						}
					}
					if (Math.abs(i - row) == Math.abs(j - colum)) {
						if (i > row && j > colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i - m][j - m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else if (i > row && j < colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i - m][j + m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else if (i < row && j < colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i + m][j + m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						} else if (i < row && j > colum) {
							for (int m = 1; m <= Math.abs(i - row); ++m) {
								if (cells[i + m][j - m].getFlag() != 0)
									break;
								if (m == Math.abs(i - row)) {
									cells[row][colum].setFlag(cells[i][j].getFlag());
									cells[row][colum].setChoose(true);
									cells[i][j].setFlag(0);
									cells[i][j].setChoose(false);
									side = 1;
									break outloop;
								}
							}
						}
					}
				}
			}
		}
	}

	public void king(Cell[][] cells, int row, int colum) {
		outloop: for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].isChoose()) {
					if (i == row && Math.abs(j - colum) == 1) {
						cells[row][colum].setFlag(cells[i][j].getFlag());
						cells[row][colum].setChoose(true);
						cells[i][j].setFlag(0);
						cells[i][j].setChoose(false);
						side = 1;
					} else if (j == colum && Math.abs(i - row) == 1) {
						cells[row][colum].setFlag(cells[i][j].getFlag());
						cells[row][colum].setChoose(true);
						cells[i][j].setFlag(0);
						cells[i][j].setChoose(false);
						side = 1;
					}
					if (Math.abs(i - row) == 1 && Math.abs(j - colum) == 1) {
						cells[row][colum].setFlag(cells[i][j].getFlag());
						cells[row][colum].setChoose(true);
						cells[i][j].setFlag(0);
						cells[i][j].setChoose(false);
						side = 1;
					}
					break outloop;
				}
			}
		}
	}

	public void getCanWalk(Cell[][] cells) {
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				Cell c = cells[i][j];
				ArrayList<Pos> walks = c.getCanWalk();
				if (c.getFlag() == 1) {
					for (int m = 0; m < 8; ++m) {
						for (int n = 0; n < 8; ++n) {
							if (cells[m][n].getFlag() * c.getFlag() <= 0 && Math.abs(m - c.getRow()) == 1
									&& n == c.getColum()) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
							if (cells[m][n].getFlag() * c.getFlag() <= 0 && Math.abs(n - c.getColum()) == 1
									&& m == c.getRow()) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
							if (cells[m][n].getFlag() * c.getFlag() <= 0 && Math.abs(m - c.getRow()) == 1
									&& Math.abs(n - c.getColum()) == 1) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
						}
					}
				}
				if (c.getFlag() == 2) {
					int k = 1;
					while (j + k < 8 && cells[i][j + k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (j - k >= 0 && cells[i][j - k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && cells[i + k][j].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i + k, j);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && cells[i - k][j].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i - k, j);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && j + k < 8 && cells[i + k][j + k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i + k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && j - k >= 0 && cells[i + k][j - k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i + k, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j + k < 8 && cells[i - k][j + k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i - k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j - k >= 0 && cells[i - k][j - k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i - k, j - k);
						walks.add(p);
						k++;
					}
				}
				if (c.getFlag() == 3) {
					int k = 1;
					while (i + k < 8 && j + k < 8 && cells[i + k][j + k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i + k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && j - k >= 0 && cells[i + k][j - k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i + k, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j + k < 8 && cells[i - k][j + k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i - k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j - k >= 0 && cells[i - k][j - k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i - k, j - k);
						walks.add(p);
						k++;
					}
				}
				if (c.getFlag() == 4) {
					for (int m = 0; m < 8; ++m) {
						for (int n = 0; n < 8; ++n) {
							if (cells[m][n].getFlag() * c.getFlag() <= 0 && Math.abs(m - c.getRow()) == 2
									&& Math.abs(n - c.getColum()) == 1) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
							if (cells[m][n].getFlag() * c.getFlag() <= 0 && Math.abs(m - c.getRow()) == 1
									&& Math.abs(n - c.getColum()) == 2) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
						}
					}
				}
				if (c.getFlag() == 5) {
					int k = 1;
					while (j + k < 8 && cells[i][j + k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (j - k >= 0 && cells[i][j - k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && cells[i + k][j].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i + k, j);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && cells[i - k][j].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i - k, j);
						walks.add(p);
						k++;
					}
				}
				if (c.getFlag() == 6) {
					if (c.getRow() == 1) {
						int k = 1;
						while (k <= 2 && i + k < 8 && cells[i + k][j].getFlag() == 0) {
							Pos p = new Pos(i + k, j);
							walks.add(p);
							k++;
						}
					} else {
						if (c.getRow() + 1 < 8 && cells[c.getRow() + 1][c.getColum()].getFlag() == 0) {
							Pos p = new Pos(c.getRow() + 1, c.getColum());
							walks.add(p);
						}
					}
					if (c.getRow() + 1 < 8 && c.getColum() - 1 >= 0
							&& cells[c.getRow() + 1][c.getColum() - 1].getFlag() < 0) {
						Pos p = new Pos(c.getRow() + 1, c.getColum() - 1);
						walks.add(p);
					} else if (c.getRow() + 1 < 8 && c.getColum() + 1 < 8
							&& cells[c.getRow() + 1][c.getColum() + 1].getFlag() < 0) {
						Pos p = new Pos(c.getRow() + 1, c.getColum() + 1);
						walks.add(p);
					}
				}
				if (c.getFlag() == -1) {
					for (int m = 0; m < 8; ++m) {
						for (int n = 0; n < 8; ++n) {
							if (cells[m][n].getFlag() == 0 && Math.abs(m - c.getRow()) == 1 && n == c.getColum()) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
							if (cells[m][n].getFlag() == 0 && Math.abs(n - c.getColum()) == 1 && m == c.getRow()) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
							if (cells[m][n].getFlag() == 0 && Math.abs(m - c.getRow()) == 1
									&& Math.abs(n - c.getColum()) == 1) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
						}
					}
				}
				if (c.getFlag() == -2) {
					int k = 1;
					while (j + k < 8 && cells[i][j + k].getFlag() == 0) {
						Pos p = new Pos(i, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (j - k >= 0 && cells[i][j - k].getFlag() == 0) {
						Pos p = new Pos(i, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && cells[i + k][j].getFlag() == 0) {
						Pos p = new Pos(i + k, j);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && cells[i - k][j].getFlag() == 0) {
						Pos p = new Pos(i - k, j);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && j + k < 8 && cells[i + k][j + k].getFlag() == 0) {
						Pos p = new Pos(i + k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && j - k >= 0 && cells[i + k][j - k].getFlag() == 0) {
						Pos p = new Pos(i + k, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j + k < 8 && cells[i - k][j + k].getFlag() == 0) {
						Pos p = new Pos(i - k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j - k >= 0 && cells[i - k][j - k].getFlag() == 0) {
						Pos p = new Pos(i - k, j - k);
						walks.add(p);
						k++;
					}
				}
				if (c.getFlag() == -3) {
					int k = 1;
					while (i + k < 8 && j + k < 8 && cells[i + k][j + k].getFlag() == 0) {
						Pos p = new Pos(i + k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && j - k >= 0 && cells[i + k][j - k].getFlag() == 0) {
						Pos p = new Pos(i + k, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j + k < 8 && cells[i - k][j + k].getFlag() == 0) {
						Pos p = new Pos(i - k, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && j - k >= 0 && cells[i - k][j - k].getFlag() == 0) {
						Pos p = new Pos(i - k, j - k);
						walks.add(p);
						k++;
					}
				}
				if (c.getFlag() == -4) {
					for (int m = 0; m < 8; ++m) {
						for (int n = 0; n < 8; ++n) {
							if (cells[m][n].getFlag() * c.getFlag() <= 0 && Math.abs(m - c.getRow()) == 2
									&& Math.abs(n - c.getColum()) == 1) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
							if (cells[m][n].getFlag() * c.getFlag() <= 0 && Math.abs(m - c.getRow()) == 1
									&& Math.abs(n - c.getColum()) == 2) {
								Pos p = new Pos(m, n);
								walks.add(p);
							}
						}
					}
				}
				if (c.getFlag() == -5) {
					int k = 1;
					while (j + k < 8 && cells[i][j + k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i, j + k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (j - k >= 0 && cells[i][j - k].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i, j - k);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i + k < 8 && cells[i + k][j].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i + k, j);
						walks.add(p);
						k++;
					}
					k = 1;
					while (i - k >= 0 && cells[i - k][j].getFlag() * c.getFlag() <= 0) {
						Pos p = new Pos(i - k, j);
						walks.add(p);
						k++;
					}
				}
				if (c.getFlag() == -6) {
					if (c.getRow() == 1) {
						int k = 1;
						while (k <= 2 && i + k < 8 && cells[i + k][j].getFlag() == 0) {
							Pos p = new Pos(i + k, j);
							walks.add(p);
							k++;
						}
					} else {
						if (c.getRow() + 1 < 8 && cells[c.getRow() + 1][c.getColum()].getFlag() == 0) {
							Pos p = new Pos(c.getRow() + 1, c.getColum());
							walks.add(p);
						}
					}
				}
			}
		}
	}

	public void pcMove(Cell[][] cells, Draw d) {
		List<Cell> cs = new ArrayList<Cell>();
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (cells[i][j].getFlag() > 0)
					cs.add(cells[i][j]);
			}
		}
		Random rand = new Random();
		while (true) {
			Cell c = cs.get(rand.nextInt(cs.size()));
			if (c.getCanWalk().size() != 0) {
				Pos p = c.getCanWalk().get(rand.nextInt(c.getCanWalk().size()));
				cells[p.getRow()][p.getColum()].setFlag(c.getFlag());
				cells[c.getRow()][c.getColum()].setFlag(0);
				d.highLight();
				break;
			}
		}
	}
}
